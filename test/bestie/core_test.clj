(ns bestie.core-test
  (:require [clojure.test :refer :all]
            [bestie.core :refer :all]))

(deftest url-test
  (testing "creating a Best Buy API URL without search"
    (let [api "products"
          params {:apiKey "lol"}
          the-url (url api params)]
      (is (= "https://api.bestbuy.com/v1/products?apiKey=lol&format=json" the-url))))
  (testing "creating a Best Buy API with a search component"
    (let [api "products"
          params {:search "(upc=lol)"
                  :apiKey "lol"}
          the-url (url api params)]
      (is (= "https://api.bestbuy.com/v1/products(upc=lol)?apiKey=lol&format=json")))))
