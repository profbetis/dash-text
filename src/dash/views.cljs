(ns dash.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.util :as util]
            [dash.core :as core]
            ))

(defn view-a [cursor]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h1 nil "This is View A")
      (dom/p nil "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
      ))))

(defn view-b [cursor]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h1 nil "This is View B")
      (dom/p nil "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB")
      ))))

(defn view-c [cursor]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h1 nil "This is View C")
      (dom/p nil "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC")
      ))))

(defn view-d [cursor]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h1 nil "This is View D")
      (dom/p nil "DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD")
      ))))

(defn tab-view [cursor]
  (reify om/IRender (render [_]
    (let [cur-view (first (:current-tab cursor))
          tabs (:tab-list cursor)
         ]
    ; Note: Need to have a style for invisible divs.
    ; Om functions only work on one root element so it must be a div to contain more elements.
    ; Alternatively, figure out how to work with seq or similar to not need a parent div.
    (dom/div #js {:className "hidden-div"}

        (for [i (range 0 (count tabs))]
            (dom/button ;#js {style=(/ 100 (count tabs) + '%')}
            (if (= cur-view i)
              #js {:className "tab" :disabled true}
              #js {:className "tab" :onClick #(om/update! (:current-tab cursor) [0] i)})
            (str "Tab " i)))

        (om/build (nth tabs cur-view) nil)

        ; (dom/button
        ;   (if (= current-view 0)
        ;     #js {:className "view-switcher" :disabled true}
        ;     #js {:className "view-switcher" :onClick #(om/update! cursor [0] 0)})
        ;   (str "View A"))
    
        ; (dom/button
        ;   (if (= current-view 1)
        ;     #js {:className "view-switcher" :disabled true}
        ;     #js {:className "view-switcher" :onClick #(om/update! cursor [0] 1)})
        ;   (str "View B"))

        ; (dom/button
        ;   (if (= current-view 2)
        ;     #js {:className "view-switcher" :disabled true}
        ;     #js {:className "view-switcher" :onClick #(om/update! cursor [0] 2)})
        ;   (str "View C"))
    )))))

(defn dash-loading []
  (reify om/IRender (render [_]
  (dom/div #js {:className "dash-loading"} "Loading - Please Wait!"))))

(defn tabs-view [cursor owner] ;META
  (reify om/IRender (render [_]
    (dom/div #js {:id "test-container"}
      (dom/h1 nil "Tabs View")
      (om/update! cursor [:tab-list] [view-a view-b view-c view-d])
      (if-not (= [] (:tab-list cursor))
        (om/build tab-view cursor)
        (om/build dash-loading cursor))
      ))))
