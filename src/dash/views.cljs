(ns dash.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.util :as util]
            [dash.core :as core]
            ))

(defn view-switcher [cursor]
  (reify om/IRender (render [_]
    (let [current-view (get cursor 0)]

    (doseq [v [0 1]]
      (dom/button #js {:className "view-switcher" :disabled false }
        (if (= current-view v)
          #js {:disabled true }
          #js {:onClick #(om/transact! [:view 0] v)})
        (str "View " current-view)))
    ))))

(defn view-a [cursor]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h1 nil "This is View A")
      (dom/h3 nil (str "(Also known in the atom as View " (str (get-in cursor [:view 0])) ")"))
      (dom/p nil "AAAAAAAAAAAA")
      (dom/button #js {:className "test-button" :onClick #(.log js/console "Hello!")} "Hello Test")
      (om/build view-switcher (:view cursor))
      ))))

(defn view-b [cursor]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h1 nil "This is View B")
      (dom/h3 nil (str "(Also known in the Atom as View " (str (get-in cursor [:view 0])) ")"))
      (dom/p nil "BBBBBBBBBBBB")
      (dom/button #js {:className "test-button" :onClick #(.log js/console "Hello!")} "Hello Test")
      (om/build view-switcher (:view cursor))
      ))))

(defn simple-view [cursor]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h2 nil "Simple Cursor Test View")
      (dom/p nil (str "I am attempting to retrieve a value from the atom via cursors.
                      The value I'm expecting is 0. The value I find is " (get-in cursor [:view 0])))
    ))))

(defn views-view [cursor owner] ;META
  (reify om/IRender (render [_]
    (dom/div #js {:id "test-container"}
      (dom/h1 nil "Views View")
      (let [current-view (get-in cursor [:view 0])]
        (om/build simple-view cursor)
        (cond
          (= current-view 0) (om/build view-a cursor)
          (= current-view 1) (om/build view-b cursor)
          :else (dom/h3 nil "No View to Render"))
        )))))
