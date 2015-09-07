(ns dash.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.util :as util]
            [dash.core :as core]
            ))

(defn view-switcher [state]
  (reify om/IRender (render [_]
    (let [current-view (:view state)]

    (dom/button (if (= current-view 0)
                #js {:className "view-switcher-disabled" :disabled true }
                #js {:className "view-switcher" :disabled false :onClick (dash.core/upsert-view state 0)})
                (str "View " current-view))
    ; (dom/button (if (= current-view 1)
    ;             #js {:className "view-switcher-disabled" :disabled true}
    ;             #js {:className "view-switcher" :disabled false :onClick (dash.core/upsert-view state 1)})
    ;             (str "View" current-view))
    ; (dom/button (if (= current-view 2)
    ;             #js {:className "view-switcher-disabled" :disabled true}
    ;             #js {:className "view-switcher" :disabled false :onClick (dash.core/upsert-view state 2)})
    ;             (str "View" current-view))
    ))))

(defn view-a [state]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h3 nil "This is View A")
      (dom/h2 nil (str "(Also known in the atom as View " (str (:view state)) ")"))
      (dom/p nil "AAAAAAAAAAAA")
      (dom/button #js {:className "test-button" :onClick (.log js/console "Hello!")} "Test")
      (om/build view-switcher state)
      ))))

(defn view-b [state]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h3 nil "This is View B")
      (dom/h2 nil (str "(Also known in the atom as View " (str (:view state)) ")"))
      (dom/p nil "BBBBBBBBBBBB")
      (dom/button #js {:className "test-button" :onClick nil} "Hello")
      (om/build view-switcher state)
      ))))

(defn view-c [state]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h3 nil "This is View C")
      (dom/h2 nil (str "(Also known in the atom as View " (str (:view state)) ")"))
      (dom/p nil "CCCCCCCCCCCC")
      (dom/button #js {:className "test-button" :onClick nil} "Hello")
      (om/build view-switcher state)
      ))))

(defn views-view [state owner] ;META
  (reify om/IRender (render [_]
    (dom/div #js {:id "test-container"}
      (dom/h3 nil "Views View")
      (om/build view-a state)
      ))))
