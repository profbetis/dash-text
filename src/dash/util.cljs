(ns dash.util
  (:require [clojure.string :as string]
            [cljs.reader :as reader]
            [cognitect.transit :as transit]))

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

(defn untrans [x]
  (let [r (transit/reader :json)]
    (transit/read r x)))
