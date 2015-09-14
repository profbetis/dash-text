(ns dash.canvas
	(:require [om.core :as om :include-macros true]
	          [om.dom :as dom :include-macros true]
	          [dash.util :as util]
	          [dash.core :as core]
	          [quil.core :as q :include-macros true]
	          ))

(defn setup []
	(q/no-smooth)
	(q/frame-rate 6)
	(q/background 24)
	(q/text-align :center :center)
	(q/text-size 8)
	)

(defn graph-test [globals]
	(let [
		  w (:width globals)
		  h (:height globals)
		  data [0.5 34.2 -0.505 -4.03 12.5 25.5 50 50 50 200 100 0 
				-25.5 40.2 86 92 -100 -50 -33.33 5 10 -22 -30 90 50]
		  num-entries (count data)
		  max-value (apply max data)
		  min-value (apply min data)
		  largest-val (apply max (mapv Math.abs data))
		  value-range (- max-value min-value)
		  range-mid (+ (* value-range 0.5) min-value)
		  data-squash 0.2
		  view-top (+ range-mid (* value-range (+ 0.5 data-squash)))
		  view-bottom (- range-mid (* value-range (+ 0.5 data-squash)))
		  num-to-px #(q/map-range % view-top view-bottom 16 (- h 16))
		  origin (num-to-px 0)
		 ]

		; Top bar
		(q/fill 42)
		(q/stroke 0 0)
		(q/rect 0 0 w 16)

		(q/stroke 0 255 204)

		; Data entries
		(doseq [i (range 0 num-entries)]
			(let [
				  value (nth data i)
				  next-value (if (< (+ i 1) num-entries)
					  	(nth data (+ i 1))
					  	value)
				  x (/ (* (+ 1 i) w) (+ num-entries 1))
				  y (num-to-px value)
				  nx (/ (* (+ 2 i) w) (+ num-entries 1))
				  ny (if (< (+ i 1) num-entries)
					  	(num-to-px next-value)
					  	y)
				  amplitude (/ (Math.abs value) largest-val)
				  change (util/normalize (* -1 largest-val) largest-val (- next-value value))
				 ]

				; Circles at data points
				(q/fill 0 255 204 (* 255 (/ (q/abs value) largest-val)))
				(q/stroke 0 0)
				(q/ellipse x y 6 6)
				(q/stroke 0 255 204 64)

				; Interval Lines
				(q/line x origin x y)
				(q/stroke 255 16)
				(q/line x 16 x h)
				(q/stroke 0 255 204 64)

				; Text at data point

				(if (>= value 0)
					(q/text-num value x (- y 12))
					(q/text-num value x (+ y 12)))

				; Graph fill
				(q/fill (util/lerp 255 0 change)
						(util/lerp 0 255 change)
						204 
						64)
				(if (< (+ i 1) num-entries)
					(q/quad x origin  x y  nx ny  nx origin)
					nil)
			))
	))

(defn circle-splat [globals]
	(let [
		  diam (q/random 50)
	      x    (q/random (q/width))
          y    (q/random (q/height))
          w (:width globals)
          h (:height globals)
         ]

		(q/stroke (q/random 255))
		(q/stroke-weight (q/random 10))
		(q/fill (q/random 255))

    	(q/ellipse x y diam diam)
    	(q/ellipse x (- h y) diam diam)
    	(q/ellipse (- w x) y diam diam)
    	(q/ellipse (- w x) (- h y) diam diam)
    ))

(defn draw []
  	(let [
  			w (q/width)
  			h (q/height)
  			globals {:width w :height h}
         ]

         (q/background 28)
         (graph-test globals)
        ;(circle-splat globals)

    	))

(defn start-sketch [cursor]
	;(sample-data [(om/ref-cursor (:name (om/root-cursor app-state)))])
	(q/sketch
		:host "canvas-test"
		:title "Grapher"
		:setup setup
		:draw draw
		:size [640 256]))