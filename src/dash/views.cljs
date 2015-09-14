(ns dash.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.util :as util]
            [dash.core :as core]
            [dash.canvas :as canvas]
            ))

(defn dash-loading []
  (reify om/IRender (render [_]
  (dom/div #js {:className "dash-loading"} "Loading - Please Wait!"))))

(defn view-a [cursor]
  (reify om/IRender (render [_]
    (dom/div #js {:className "hidden-div"}
      (dom/h1 nil "This is View A")))))

(defn view-b [cursor]
  (reify om/IRender (render [_]
    (dom/div #js {:className "hidden-div"}
      (dom/h1 nil "This is View B")))))

(defn view-c [cursor]
  (reify om/IRender (render [_]
    (dom/div #js {:className "hidden-div"}
      (dom/h1 nil "This is View C")))))

(defn view-d [cursor]
  (reify om/IRender (render [_]
    (dom/div #js {:className "hidden-div"}
      (dom/h1 nil "This is View D")))))

(defn tab-view [cursor]
  (reify om/IRender (render [_]
    (let [cur-view (first (:root-cur-tab cursor))
          tabs (:root-tab-list cursor)
          names ["View A" "View B" "View C" "View D"]
          num-tabs (count tabs)]
    (dom/div nil
        (dom/div #js {:className "tab-bar"}
          (for [i (range 0 num-tabs)]
            (let [tab-name (nth names i)]
              (dom/button
                (if (= cur-view i)
                  #js {:className (str "tab numtabs-" num-tabs) :disabled true}
                  #js {:className (str "tab numtabs-" num-tabs) :onClick #(om/update! (:root-cur-tab cursor) [0] i)})
                tab-name))))

        (om/build (nth tabs cur-view) nil)
    )))))

(defn canvas-test-view [cursor]
  (reify om/IRender (render [_]
    (let []
    (dom/div nil
      (dom/h1 nil "Canvas View")
      (dom/canvas #js {:id "canvas-test" :className "canvas-graph" :width 640 :height 256})
      (canvas/start-sketch cursor)
      (dom/h4 nil "If this viewport is black and blank, reload the page!")
    )))))

(defn text-test-view [cursor]
  (reify om/IRender (render [_]
    (let []
    (dom/div nil
      (dom/h1 nil "Text View")
      (dom/p nil "The next text grabs a static element from the state atom, and also a dynamically changing one")
      (dom/p nil (:sample-text cursor))
      (dom/p nil (str "Reload Count: " (:reload-count cursor)))
      )))))

(defn tab-test-view [cursor]
  (reify om/IRender (render [_]
    (let []
    (dom/div nil
      (dom/h1 nil "Tab View")
      (dom/p nil "The fact that you're seeing this means you're seeing this through a real tab-view (but here are some more)!")
      )))))

(defn root-view [cursor owner]
  (reify om/IRender (render [_]
    (dom/div #js {:id "test-container"}
      (dom/h3 nil "Test Container")
      (om/update! cursor [:root-tab-list] [text-test-view 
                                      canvas-test-view 
                                      tab-test-view])
      (om/build tab-view cursor)
      ))))
