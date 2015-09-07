(ns dash.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.util :as util]
            [dash.core :as core]))

(defn change-view [cursor view-id]
  (om/transact! cursor [0] view-id))

(defn view-switcher [ cursor ]
  (reify om/IRender (render [_]
    (let [current-view cursor]
    (dom/button (if-not (= current-view 0)
                #js {:className "view-switcher" :onClick (change-view cursor 0)}
                #js {:className "view-switcher-disabled" :onClick nil})
                "View A")
    (dom/button (if-not (= current-view 1)
                #js {:className "view-switcher" :onClick (change-view cursor 1)}
                #js {:className "view-switcher-disabled" :onClick nil})
                "View B")
    (dom/button (if-not (= current-view 2)
                #js {:className "view-switcher" :onClick (change-view cursor 2)}
                #js {:className "view-switcher-disabled" :onClick nil})
                "View C")
    ))))

(defn view-a [cursor]
  (reify om/IRender (render [_]
    (dom/h2 nil "This is View A")
    (dom/h3 nil (str "(Also known in the atom as View " (get cursor :view)))
    (dom/p nil "AAAAAAAAAAAA")
    (om/build view-switcher (get cursor :view)))))

(defn view-b [cursor]
  (reify om/IRender (render [_]
    (dom/h2 nil "This is View B")
    (dom/h3 nil (str "(Also known in the atom as View " (get cursor :view)))
    (dom/p nil "BBBBBBBBBBBB")
    (om/build view-switcher (get cursor :view)))))

(defn view-c [cursor]
  (reify om/IRender (render [_]
    (dom/h2 nil "This is View C")
    (dom/h3 nil (str "(Also known in the atom as View " (get cursor :view)))
    (dom/p nil "CCCCCCCCCCCC")
    (om/build view-switcher (get cursor :view)))))

(defn views-view [cursor owner] ;META
  (reify om/IRender (render [_]
    (dom/div #js {:id "test-container"}
      (dom/h3 nil "Views View")
      (om/build view-a (cursor))))))
