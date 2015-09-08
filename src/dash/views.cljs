(ns dash.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.util :as util]
            [dash.core :as core]
            ))

(def view {:cur-view 0})

(defn view-switcher [state]
  (reify om/IRender (render [_]
    (let [current-view (:view state)]

    (dom/button #js {:className "view-switcher" :disabled false }
      (if (= current-view 0)
        #js {:disabled true}
        #js {:onClick #(dash.core/upsert-view state 0)})
      (str "View " current-view))

    (dom/button #js {:className "view-switcher" :disabled false }
      (if (= current-view 1)
        #js {:disabled true}
        #js {:onClick #(dash.core/upsert-view state 1)})
      (str "View " current-view))

    (dom/button #js {:className "view-switcher" :disabled false }
      (if (= current-view 2)
        #js {:disabled true}
        #js {:onClick #(dash.core/upsert-view state 2)})
      (str "View " current-view))

    ; (for [v (range 0 2)
    ;   (dom/button #js {:className "view-switcher" :disabled false }
    ;   (if (= current-view v)
    ;     #js {:disabled true }
    ;     #js {:onClick (dash.core/upsert-view state v)})
    ;   (str "View " current-view))])
    ))))

(defn view-a [state]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h1 nil "This is View A")
      (dom/h3 nil (str "(Also known in the atom as View " (str (:view state)) ")"))
      (dom/p nil "AAAAAAAAAAAA")
      (dom/button #js {:className "test-button" :onClick #(.log js/console "Hello!")} "Hello Test")
      (om/build-all view-switcher state)
      ))))

(defn view-b [state]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h1 nil "This is View B")
      (dom/h3 nil (str "(Also known in the atom as View " (str (:view state)) ")"))
      (dom/p nil "BBBBBBBBBBBB")
      (dom/button #js {:className "test-button" :onClick #(.log js/console "Hello!")} "Hello Test")
      (om/build-all view-switcher state)
      ))))

(defn view-c [state]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h1 nil "This is View C")
      (dom/h3 nil (str "(Also known in the atom as View " (str (:view state)) ")"))
      (dom/p nil "CCCCCCCCCCCC")
      (dom/button #js {:className "test-button" :onClick #(.log js/console "Hello!")} "Hello Test")
      (om/build-all view-switcher state)
      ))))

(defn views-view [state owner] ;META
  (reify om/IRender (render [_]
    (dom/div #js {:id "test-container"}
      (dom/h1 nil "Views View")
      (let [view-to-render (:cur-view view)]
        (cond
          (= view-to-render 0) (om/build view-a state)
          (= view-to-render 1) (om/build view-b state)
          (= view-to-render 2) (om/build view-c state)
          :else (dom/h3 nil "No View to Render")
        ))))))
