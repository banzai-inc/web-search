(ns web-search.deprecated.google
  (:require [web-search.core :as core]
            [clj-http.client :as http]
            [cheshire.core :as json]
            [cemerick.url :refer (url url-encode)]))

(def ^{:private true} base-url
  "http://ajax.googleapis.com/ajax/services/search/web")

(def defaults {:version "1.0"})

(defn- to-url
  "Construct URL"
  [query]
  (-> (url base-url)
      (assoc :query {:q query
                     :v (:version defaults)})
      (str)))

(defn as-results [raw]
  "Takes raw search results and returns WebResult records"
  (map (fn [r] (core/->WebResult (:title r) (:url r) (:content r))) raw))

(defn search
  [query]
  (-> (to-url query)
      (http/get)
      (:body)
      (json/parse-string true)
      (get-in [:responseData :results])
      (as-results)))
