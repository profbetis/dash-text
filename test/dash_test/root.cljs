(ns dash-test.root
  (:require [om.core :as om :include-macros true]
            [dash-test.core :as dash-core]
            [dash-test.views :as dash-views]
            [dash-test.util :as dash-util]))

(enable-console-print!)

(defonce test-state (atom {}))

(dash-core/refresh-tests! test-state)

(om/root
  dash-views/ns-tests-view
  test-state
  {:target (. js/document (getElementById "tests"))})
