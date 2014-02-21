;; Uses Google Custom Search API
;; 
;; Find your own Custom Search engine at https://www.google.com/cse.
;; To search the entire web, http://stackoverflow.com/questions/6099663/alternative-to-the-deprecated-google-rest-web-search-api
;;
(ns web-search.google
  (:require [web-search.core :as core]
            [clj-http.client :as http]
            [cheshire.core :as json]
            [cemerick.url :refer (url url-encode)]))

(def ^{:private true} base-url
  "https://www.googleapis.com/customsearch/v1?")

(defn- to-url
  "Construct REST url"
  [api-key search-engine-id query]
  (-> (url base-url)
      (assoc :query {:key api-key :cx search-engine-id :q query})
      (str)))

(defn- as-results
  "Takes raw search results and returns WebResult records"
  [raw]
  (map (fn [r] (core/->WebResult (:title r) (:link r) (:snippet r))) raw))

(defn search
  [{:keys [api-key search-engine-id]} query]
  (-> (to-url api-key search-engine-id query)
      (http/get)
      (:body)
      (json/parse-string true)
      (:items)
      (as-results)))
