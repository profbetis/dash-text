(ns dash.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [dash.util :as dash-util]
            [clojure.string :as string]
            [cljs.reader :as reader]
            [goog.events :as events]
            [cljs.core.async :as async :refer [put! chan <!]]
            [ajax.core :as ajax :refer [GET]]))

(defn- upsert-item [state item & ks]
  "Insert or update an item the given atom at the given keys [ks].
   Item should be a map containing a :name string."
  (let [kw-name (dash-util/name-as-kw (:name item))]
    (swap! state
      (assoc-in @state (conj ks kw-name) (dash-util/ensure-has-id item)))))

(defn upsert-task [state task]
  "Insert or update a task in the given atom."
  (upsert-item state task :lists))

(defn ok-handler [state]
  (fn [result]
    (reset! state result)))

(defn error-handler [{:keys [status status-text]}]
    (.log js/console (str "http request error: " status " " status-text)))

(defn fetch-updates [uri state]
  "Return the map with any pending updates applied."
    (GET uri
         :handler (ok-handler state)
         :response-format (ajax/transit-response-format)
         :error-handler error-handler))
