(ns dash.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.util :as dash-util]))

(defn login-view []
  (reify om/IRender (render [_]
    (dom/div #js {:id "login-container"}
      (dom/h2 nil "Log-in to Dash")
      (dom/form {:class "login-form" :action "http://localhost:3449/test-server/login" }
        (dom/text #js {:class "login-label"} "Email")
        (dom/input #js {:type "text" :name "email"})
        (dom/text #js {:class "login-label"} "Password")
        (dom/input #js {:type "password" :name "pass"})
        (dom/input #js {:type "submit" :value "Log-in"}))))))

(defn login-table-view []
  (reify om/IRender (render [_]
    (dom/div #js {:id "login-container"}
      (dom/h3 nil "Log-in to Dash")
      (dom/form {:class "login-form"}
        (dom/table {:class "login-table"}
          (dom/tr #js {:class "login-labels"}
            (dom/td "Email")
            (dom/td "Password"))
          (dom/tr #js {:class "login-elements"}
            (dom/td (dom/input #js {:type "text" :name "email"}))
            (dom/td (dom/input #js {:type "password" :name "pass"}))
            (dom/td (dom/input #js {:type "submit" :value "Log-in"})))))))))

(defn login-test-view []
  (reify om/IRender (render [_]
    (dom/div #js {:id "test-container"}
      (dom/h3 nil "Login Test View")
      (om/build login-view ())))))
