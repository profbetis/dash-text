(ns dash.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.util :as util]
            [dash.core :as core]))

(defn view-switcher [state]
  (reify om/IRender (render [_]
    (let [current-view (:view state)]
    (dom/button (if-not (= current-view 0)
                #js {:className "view-switcher" :onClick (core/upsert-view state 0)}
                #js {:className "view-switcher-disabled" :onClick nil})
                "View A")
    (dom/button (if-not (= current-view 1)
                #js {:className "view-switcher" :onClick (core/upsert-view state 1)}
                #js {:className "view-switcher-disabled" :onClick nil})
                "View B")
    (dom/button (if-not (= current-view 2)
                #js {:className "view-switcher" :onClick (core/upsert-view state 2)}
                #js {:className "view-switcher-disabled" :onClick nil})
                "View C")
    ))))

(defn view-a [state]
  (reify om/IRender (render [_]
    (dom/h2 nil "This is View A")
    (dom/p nil "AAAAAAAAAAAA")
    (om/build view-switcher (state)))))

(defn view-b [state]
  (reify om/IRender (render [_]
    (dom/h2 nil "This is View B")
    (dom/p nil "BBBBBBBBBBBB")
    (om/build view-switcher (state)))))

(defn view-c [state]
  (reify om/IRender (render [_]
    (dom/h2 nil "This is View C")
    (dom/p nil "CCCCCCCCCCCC")
    (om/build view-switcher (state)))))

(defn views-view [state owner] ;META
  (reify om/IRender (render [_]
    (dom/div #js {:id "test-container"}
      (dom/h3 nil "Views View")
      (om/build view-a (state))))))
