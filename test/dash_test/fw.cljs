(ns dash-test.fw
  (:require [dash-test.core :as dash-core]
            [dash-test.root :as dash-root]))

(defn on-jsload []
  "For Figwheel.  This gets run on JS load."
  (do (dash-core/refresh-tests! dash-root/test-state)
      (println "refresh")))
