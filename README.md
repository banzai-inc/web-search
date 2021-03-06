# web-search

A Clojure library with a set of tools for searching the web (Google).

## Installation

Leiningen:

```
[web-search "0.1.0"]
```

## Usage

### Google Custom Search

Follow instructions here to create your Custom Search engine: https://developers.google.com/custom-search/json-api/v1/overview.

Then, if you want to search the entire web, follow this: http://stackoverflow.com/questions/6099663/alternative-to-the-deprecated-google-rest-web-search-api.

```clojure
(require '[web-search.google :as g])

(g/search {:api-key "mykey" :search-engine-id "my-custom-search-id"} "hello world")
```

### Google Web Search API (Deprecated)

```clojure
(require '[web-search.deprecated.google :as g])

(g/search "hello world")
```

## License

Copyright © 2014 Banzai Inc.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
