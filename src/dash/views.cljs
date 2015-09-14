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
      (dom/h1 nil "Canvas View")
      (canvas/start-sketch cursor)
      (dom/canvas #js {:id "canvas-test" :className "canvas-graph" :width 640 :height 256})
      (dom/h4 nil "If this viewport is black and blank, reload the page!")
    )))))

(defn text-view [cursor]
  (reify om/IRender (render [_]
    (let []
    (dom/div nil
      (dom/h1 nil "Text View")
      (dom/p nil "The next text grabs a static element from the state atom, and also a dynamically changing one")
      (dom/p nil (:sample-text cursor))
      (dom/p nil (str "Reload Count: " (:reload-count cursor)))
      )))))

(defn tab-view [cursor]
  (reify om/IRender (render [_]
    (let []
    (dom/div nil
      (dom/h1 nil "Tab View")
      (dom/p nil "The fact that you're seeing this means you're seeing tabs already (but here are some more)!")
      )))))

(defn root-view [cursor owner]
  (reify om/IRender (render [_]
    (dom/div #js {:id "test-container"}
      (dom/h3 nil "Test Container")
      (om/build text-view cursor)
      (om/build canvas-view cursor)
      (om/build tab-view cursor)
      ))))
