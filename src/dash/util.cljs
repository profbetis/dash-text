(ns dash.util
  (:require [clojure.string :as string]))

(defn name-as-id [some-name]
  "Cleanse a named thing into a usable string id."
  (string/replace some-name #"[^a-zA-Z\d*+!\-;_?]" "_" ))

(defn name-as-kw [some-name]
  "Cleanse and keywordify some-name with the same rules as dash.util/name-as-id."
  (keyword (name-as-id some-name)))

(defn ensure-has-id [some-object]
  "Return an object with an :id of its keywordized :name."
  (let [kw-name (name-as-kw (:name some-object))]
    (if-not (contains? some-object :name) some-object
      (if-not (= (:id some-object kw-name))
        (assoc some-object :id kw-name)
        some-object))))

(defn abbreviate [some-name]
  "Takes the first letter of each word and makes a new word out of it"
  (let [words (string/split (string/trim (string/upper-case some-name)) #"\s+")]
    (str
      (ffirst words)
      (if (= (alength (to-array words)) 1)
        nil
        (first (last words))))))

(defn lerp [a b x]
  "Linear interpolation between two values"
  (+ a (* x (- b a))))

(defn remap [aa ab ba bb value]
  "Remaps a number in one range to another range"
   (lerp ba bb (/ (- value aa) (- ab aa)))
)

(defn normalize [a b x]
  "Normalizes a number from an arbitrary range to 0-1"
  (remap a b 0 1 x))

(defn soft-clamp
  "Softly clamps all values to between -1 and 1"
  ([x tightness] (/ (Math/atan (* x (+ tightness 1))) Math/PI))
  ([x] (soft-clamp x 0))
  )

(defn soft-clamp-pos
  "Softly clamps all values to between 0 and 1"
  ([x tightness] (+ (* (soft-clamp x tightness) 0.5) 1))
  ([x] (soft-clamp-pos x 0))
  )
