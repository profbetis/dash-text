(ns dash.root
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.views :as dash-views]
            [dash.core :as dash-core]
            [figwheel.client :as fw]))

(enable-console-print!)

(def app-state (atom {:reload-count 0}))

(fw/start {
  :on-jsload (fn [] (do (swap! app-state #(assoc % :reload-count (inc (:reload-count %))))
                        (println "refresh: " (:reload-count @app-state))))
  :build-id "dash"})

(om/root
 dash-views/elements-view
 app-state
 {:target (. js/document (getElementById "dash"))})

;(swap! app-state #((dash-core/fetch-updates "http://localhost:3449/test/test-data" %)))
(dash-core/fetch-updates "http://localhost:3449/test/test-data" app-state)
