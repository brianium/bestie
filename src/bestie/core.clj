(ns bestie.core
  "Functions for working with the Best Buy API"
  (:require [clojure.data.json :as json]
            [org.httpkit.client :as http]
            [query-string.core :as query]))

(def ^:const endpoint "https://api.bestbuy.com/v1")
(def ^:const required-params {:format "json"})

(defn- handle-response
  "Returns the response body as a map"
  [{:keys [body]}]
  (json/read-str body :key-fn #(keyword %)))

(defn- filter-params
  "Filters out params not used in query"
  [params]
  (->> (merge params required-params)
       (filter (fn [[k,v]] (not= :search k)))))

(defn url
  "Get a url for the given Best Buy API"
  [api params]
  (-> (str endpoint "/" api)
      (str (get params :search "") "?")
      (str (query/create (filter-params params)))))

(defn request
  "Performs a request against the Best Buy API"
  ([api params config]
   {:pre [(contains? params :apiKey)]}
   (-> (url api params)
       (http/get config handle-response)))
  ([api params] (request api params {})))

(defn products
  "Performs a request against the Best Buy Products API"
  ([params config] (request "products" params config))
  ([params] (request "products" params)))
