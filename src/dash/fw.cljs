(ns dash.fw
  (:require [dash.root :as dash-root]))

(defn on-jsload []
  "For Figwheel.  This gets run on JS load."
  (do (swap! dash-root/app-state #(assoc % :reload-count (inc (:reload-count %))))
      (println "refresh: " (:reload-count @dash-root/app-state))))
