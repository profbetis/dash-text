(ns dash.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.util :as util]
            [dash.core :as core]
            ))

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
      (dom/h3 nil "Login Test View")
      (om/build login-view ())))))

(defn list-view [this-list owner]
  (reify
    om/IRender (render [_]
      (let [{:keys [list-name tasks]} this-list]
        (dom/div #js {:id (str "list-" (dash-util/name-as-id list-name))}
          (dom/h3 #js {:className "list-title"} (str list-name))
          (apply dom/ul #js {:className "list-contents"}
            (map
               (fn [task] (let [{:keys [title completed date timedue]} task]
                 (dom/li
                   (if completed
                     #js {:className "task-completed"}
                     #js {:className "task-incomplete"})
                    (str title)
                    (dom/div #js {:className "info-date"}
                     date)
                    (dom/div #js {:className "info-timedue"}
                     timedue)
                     ))) tasks)))))))

(defn lists-view [app owner]
  (reify
    om/IRender (render [_]
      (dom/div #js {:id "lists-view"}
        (dom/h2 #js {:id "lists-view_title"} (str (:user app) "'s Todo Lists"))
        (apply dom/div #js {:className "list-view"}
          (om/build-all list-view (:lists app)))))))

(defn data-view [app owner]
  "A simple root Om view which renders the given app atom."
  (reify
    om/IRender (render [_]
      (dom/div nil (str @app)))))
