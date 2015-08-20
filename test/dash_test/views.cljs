(ns dash-test.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash-test.util :as util]))

(defn test-view [test-case owner]
  "test-view renders a single test div with class 'passed or 'failed."
  (reify
    om/IRender (render [_]
      (let [{:keys [id should test-fn should-be raw-fn args]} test-case]
        (dom/div
          (do
            (.log js/console (str "test " id ":") args)
            (if (= should-be (test-fn args))
              #js {:className "passed"}
              #js {:className "failed"}))
          (dom/h3 #js {:className "test-name"} (str "Test " id))
          (dom/h2 #js {:className "test-desc"} (str "should " should))
          (dom/ul nil
            ;(dom/li #js {:className "test-fn"} (str "Tests " raw-fn))
            (if args (dom/li #js {:className "test-args"} (str "Input — " args)) "")
            (dom/li #js {:className "test-result"} (str "Output — " (test-fn args)))
            (dom/li #js {:className "test-should-be"} (str "Expected Output — " should-be))
))))))

(defn tests-view [nsp-tests owner]
  "tests-view renders a vector of test maps.  Each should have a :nsp string
   and :tests vector."
  (reify
    om/IRender (render [_]
      (let [{:keys [nsp]} nsp-tests]
        (dom/div #js {:id (str "tests-" nsp) :className "ns-view"}
          (dom/h2 #js {:className "ns-title"} (str nsp ""))
          (apply dom/ul nil
            (om/build-all test-view (:tests nsp-tests))
))))))

(defn ns-tests-view [all-tests owner]
  "ns-tests-view renders all tests-views for all test namespaces."
  (reify
    om/IRender (render [_]
      (apply dom/div #js {:className "ns-views"}
        (dom/h3 #js {:id "test-title"} "Test Namespaces")
        (om/build-all tests-view (:all-tests all-tests))
))))
