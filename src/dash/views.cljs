(ns dash.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.util :as util]
            [dash.core :as core]
            [dash.canvas :as canvas]
            ))

(defn dash-loading []
  (reify om/IRender (render [_]
  (dom/div #js {:className "dash-loading"} "Loading - Please Wait!"))))

(defn canvas-view [cursor]
  (reify om/IRender (render [_]
    (let []
    (dom/div nil
      (dom/canvas #js {:id "canvas-test" :className "canvas-graph" :width 640 :height 256})
      (dom/h4 nil "If this viewport is black and blank, reload the page!")
    )))))

(defn root-view [cursor owner] ;META
  (reify om/IRender (render [_]
    (dom/div #js {:id "test-container"}
      (dom/h1 nil "Canvas View")
      (om/build canvas-view cursor)
      ))))
