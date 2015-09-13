(ns dash.views
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [dash.util :as util]
            [dash.core :as core]
            ))

(defn view-a [cursor]
  (reify om/IRender (render [_]
    (dom/div #js {:className "nomargin-div"}
      (dom/h1 nil "This is View A")
      (dom/p nil "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
      ))))

(defn view-b [cursor]
  (reify om/IRender (render [_]
    (dom/div #js {:className "nomargin-div"}
      (dom/h1 nil "This is View B")
      (dom/p nil "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB")
      ))))

(defn view-c [cursor]
  (reify om/IRender (render [_]
    (dom/div #js {:className "nomargin-div"}
      (dom/h1 nil "This is View C")
      (dom/p nil "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC")
      ))))

(defn view-d [cursor]
  (reify om/IRender (render [_]
    (dom/div #js {:className "nomargin-div"}
      (dom/h1 nil "This is View D")
      (dom/p nil "DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD")
      ))))

(defn tab-view [cursor]
  (reify om/IRender (render [_]
    (let [cur-view (first (:current-tab cursor))
          tabs (:tab-list cursor)
          num-tabs (count tabs)]
    (dom/div #js {:className "tab-view"}
        (dom/div #js {:className "tab-bar hidden-div"}
          (for [i (range 0 num-tabs)]
            (dom/button
            (if (= cur-view i)
              #js {:className (str "tab numtabs-" num-tabs) :disabled true}
              #js {:className (str "tab numtabs-" num-tabs) :onClick #(om/update! (:current-tab cursor) [0] i)})
            (str "Tab " (+ i 1)))))

        (om/build (nth tabs cur-view) nil)
    )))))

(defn canvas-view [cursor]
  (reify om/IRender (render [_]
    (let []
    (dom/div nil 
      (dom/canvas nil #js{:script "
        var c = document.getElementById('myCanvas');
        var ctx = c.getContext('2d');
        ctx.beginPath();
        ctx.arc(95,50,40,0,2*Math.PI);
        ctx.stroke();
        "})
    )))))

(defn dash-loading []
  (reify om/IRender (render [_]
  (dom/div #js {:className "dash-loading"} "Loading - Please Wait!"))))

(defn root-view [cursor owner] ;META
  (reify om/IRender (render [_]
    (dom/div #js {:id "test-container"}
      (dom/h1 nil "Canvas View")
      (om/build canvas-view cursor)
      ))))
