(ns dash.root
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.views :as dash-views]
            [dash.core :as dash-core]))

(enable-console-print!)

(defonce app-state (atom {:reload-count 0}))

(swap! app-state #(assoc % :reload-count (inc (:reload-count %))))

(om/root
 dash-views/lists-view
 app-state
 {:target (. js/document (getElementById "dash"))})

;(swap! app-state #((dash-core/fetch-updates "http://localhost:3449/test/test-data" %)))
(dash-core/fetch-updates "http://localhost:3449/test/test-data" app-state)
