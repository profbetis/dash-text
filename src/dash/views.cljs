(ns dash.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.util :as dash-util]))

(defn login-view []
  (reify om/IRender (render [_]
    (dom/div #js {:id "login-container"}
      (dom/h3 "Log-in to Dash")
      (dom/form
        (dom/p "Email > ")
        (dom/input #js {:type "text" :name "email"})
        (dom/br)
        (dom/p "Password > ")
        (dom/input #js {:type "password" :name "pass"})
        (dom/br)
        (dom/input #js {:type "submit" :value "Log-in"}))))))

(defn login-test-view []
  (reify om/IRender (render [_]
    (dom/div #js {:id "test-container"}
      (dom/h2 "Login Test View")
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
                     ))) tasks)
        ))))))

(defn lists-view [app owner]
  (reify
    om/IRender (render [_]
      (dom/div #js {:id "lists-view"}
        (dom/h2 #js {:id "lists-view_title"} (str (:user app) "'s Todo Lists"))
        (apply dom/div #js {:className "list-view"}
          (om/build-all list-view (:lists app))
      )))))
