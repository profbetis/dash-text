(ns dash.root
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.views :as dash-views]
            [dash.core :as dash-core]))

(enable-console-print!)

(defonce app-state (atom {:reload-count 0 
						  :tab-list []
						  :current-tab [0]
						  :sample-data [
						 	5.0 10.2 12.0 -4 23.3 89.45 -100.0 100.0 100.0 0.0 0.5 0.75 0.999 12.55 13.55 20.55 24.75 37.37
						  ]
						  }))

(swap! app-state #(assoc % :reload-count (inc (:reload-count %))))

(om/root
 dash-views/root-view
 app-state
 {:target (. js/document (getElementById "dash"))})

;(swap! app-state #((dash-core/fetch-updates "http://localhost:3449/test/test-data" %)))
;(dash-core/fetch-updates "http://localhost:3449/test/test-data" app-state)
