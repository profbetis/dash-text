(ns dash.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.util :as util]
            [dash.core :as core]
            ))

(defn view-switcher [cursor]
  (reify om/IRender (render [_]
    (let [current-view (first cursor)]
    (dom/div nil
        (dom/button
          (if (= current-view 0)
            #js {:className "view-switcher" :disabled true}
            #js {:className "view-switcher" :onClick #(om/update! cursor [0] 0)})
          (str "View A (" current-view ")"))
    
        (dom/button
          (if (= current-view 1)
            #js {:className "view-switcher" :disabled true}
            #js {:className "view-switcher" :onClick #(om/update! cursor [0] 1)})
          (str "View B (" current-view ")"))
    )))))

(defn view-a [cursor]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h1 nil "This is View A")
      (dom/h4 nil (str "(Also known in the atom as View " (str (get-in cursor [:view 0])) ")"))
      (dom/p nil "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
      (om/build view-switcher (:view cursor))
      ))))

(defn view-b [cursor]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h1 nil "This is View B")
      (dom/h4 nil (str "(Also known in the atom as View " (str (get-in cursor [:view 0])) ")"))
      (dom/p nil "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB")
      (om/build view-switcher (:view cursor))
      ))))

(defn views-view [cursor owner] ;META
  (reify om/IRender (render [_]
    (dom/div #js {:id "test-container"}
      (dom/h1 nil "Views View")
      (let [current-view (get-in cursor [:view 0])]
        (cond
          (= current-view 0) (om/build view-a cursor)
          (= current-view 1) (om/build view-b cursor)
          :else (dom/h3 nil "No View to Render"))
        )
      ))))
