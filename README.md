#bestie

[![Clojars Project](https://img.shields.io/clojars/v/bestie.svg)](https://clojars.org/bestie)
[![Build Status](https://travis-ci.org/brianium/bestie.svg?branch=master)](https://travis-ci.org/brianium/bestie)

A Clojure library to work with the Best Buy API

## Installation

Add the following dependency to your `project.clj` file:

```
[bestie "0.1.0"]
```

## Usage

```clojure
(ns my.app
  (:require [bestie.core :refer :all]))

;; Product Lookup Operation
(def result (products {:apiKey "my-api-key"
                       :search "(upc=12345)"}))

;; results are futures containing response as a map
(def realized @result);
```

## Functions

The main function is `request`. All functions are a specialization of this function.

### `request`

```clojure
(defn request
  ([api params config] ,,,)
  ([api params] (request api params {})))

```

* `api` is a string representing the Best Buy API of interest - i.e "items"
* `params` is a map that will be converted straight to query params
* `config` is an optional map that gets fed right to [http-kit](http://www.http-kit.org/client.html)

If `params` contains a `:search` key it will structure the url using the search syntax specified in [Best Buy's API docs](https://bestbuyapis.github.io/api-documentation/#search-techniques).

There is currently one specialized function `products` which fills in the "products" api in the `request` function

```clojure
(defn products
  ([params config] (request "products" params config))
  ([params] (requeset "products" params)))
```

`request` has a single pre-condition defined to ensure the presence of the `:apiKey` key on the `params` map.

## License

Copyright © 2016 Brian Scaturro

Distributed under the Eclipse Public License, the same as Clojure
