(ns dash.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.util :as dash-util]))

(defn elements-view [app owner]
  (reify om/IRender (render [_] 
    (dom/div #js {:id "elements-view"}
      (dom/h1 #js {:id "elements-title"} "Elements View")
      (apply dom/div #js {:className "element-view"} 
        (om/build-all element-view ( )))))))

(defn element-view []
  (reify om/IRender (render [_]
    (dom/div #js {:id "element-container"}))))

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
