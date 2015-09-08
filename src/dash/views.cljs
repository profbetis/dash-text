(ns dash.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.util :as util]
            [dash.core :as core]
            ))

(defn eval-view [cursor]
  (.log js/console (str "Evaluate View in atom via cursor: " (get-in cursor [:view 0]) ) ) )

(defn view-switcher [cursor]
  (reify om/IRender (render [_]
    (let [current-view (get cursor 0)]

    (for [v [0 1]]
      (dom/button #js {:className "view-switcher" :disabled false }
        (if (= current-view v)
          #js {:disabled true }
          #js {:onClick #(om/transact! (get cursor :view) v)})
        (str "View " current-view))
    )))))

(defn view-a [cursor]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h1 nil "This is View A")
      (dom/h3 nil (str "(Also known in the atom as View " (str (get cursor 0)) ")"))
      (dom/p nil "AAAAAAAAAAAA")
      (dom/button #js {:className "test-button" :onClick #(.log js/console "Hello!")} "Hello Test")
      (om/build-all view-switcher cursor)
      ))))

(defn view-b [cursor]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h1 nil "This is View B")
      (dom/h3 nil (str "(Also known in the Atom as View " (str (get cursor 0)) ")"))
      (dom/p nil "BBBBBBBBBBBB")
      (dom/button #js {:className "test-button" :onClick #(.log js/console "Hello!")} "Hello Test")
      (om/build-all view-switcher cursor)
      ))))

(defn simple-view [cursor]
  (reify om/IRender (render [_]
    (dom/div nil
      (dom/h2 nil "Simple Cursor Test View")
      (eval-view cursor)
      (dom/p nil (str "I am attempting to retrieve a value from the atom via cursors.\n
                  The value I'm expecting is 0. The value is " (get-in cursor [:view 0])))
      (eval-view cursor)
    ))))

(defn views-view [cursor owner] ;META
  (reify om/IRender (render [_]
    (dom/div #js {:id "test-container"}
      (dom/h1 nil "Views View")
      (let [current-view (get-in cursor [:view 0])]
        (om/build simple-view cursor)
        ; (cond
        ;   (= current-view 0) (om/build view-a (get cursor :view))
        ;   (= current-view 1) (om/build view-b (get cursor :view))
        ;   :else (dom/h3 nil "No View to Render"))
        )))))
