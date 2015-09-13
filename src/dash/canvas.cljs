(ns dash.canvas
	(:require [om.core :as om :include-macros true]
	          [om.dom :as dom :include-macros true]
	          [dash.util :as util]
	          [dash.core :as core]
	          [quil.core :as q :include-macros true]
	          ))
(defn setup []
  (q/smooth)                          ;; Turn on anti-aliasing
  (q/frame-rate 10)                   ;; Set framerate
  (q/background 200))                 ;; Set the background colour to
                                      ;; a nice shade of grey.
(defn draw []
  (q/stroke (q/random 255))             ;; Set the stroke colour to a random grey
  (q/stroke-weight (q/random 10))       ;; Set the stroke thickness randomly
  (q/fill (q/random 255))               ;; Set the fill colour to a random grey

  (let [diam (q/random 100)             ;; Set the diameter to a value between 0 and 100
        x    (q/random (q/width))       ;; Set the x coord randomly within the sketch
        y    (q/random (q/height))]     ;; Set the y coord randomly within the sketch
    (q/ellipse x y diam diam)))         ;; Draw a circle at x y with the correct diameter

(q/defsketch example                  ;; Define a new sketch named example
  :host "canvas-test"
  :title "Oh so many grey circles"    ;; Set the title of the sketch
  :setup setup                        ;; Specify the setup fn
  :draw draw                          ;; Specify the draw fn
  :size [640 256])  

(defn draw-rect
	([width] (draw-rect width width 0 0 :outline))
	([width height] (draw-rect width height 0 0 :outline))
	([width height x y] (draw-rect width height x y :outline))
	([width height x y style]
	(let [target (.getElementById js/document "canvas-test")
		  context (.getContext target "2d")]
	(q/rect context x y width height)
	)))