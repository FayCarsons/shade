A collection of color palettes for Clojure/script, taken from [MetBrewer](https://github.com/BlakeRMills/MetBrewer), [PNWColors](https://github.com/jakelawlor/PNWColors), and [WesAnderson](https://github.com/karthik/wesanderson) aw well as some utilities for parsing color palettes into CLJ/S hash-maps.

Palettes are in palette.palettes, tools are in palette.core.
External palettes may be parsed like so: 
```Clojure
    (def js-palette ["name" ["#765843" "#765843" "#765843" "#765843"]])
    (def cljs-palette (parse-palette js-palette))
    (is (= clj-palette 
                [:one [[0x76 0x58 0x43] 
                       [0x76 0x58 0x43] 
                       [0x76 0x58 0x43] 
                       [0x76 0x58 0x43]]])) => true
```
Or in batches:
```Clojure
    (def multiple-js-palettes [["one" ["#765843" "#765843" "#765843" "#765843"]]
                               ["two" ["#765843" "#765843" "#765843" "#765843"]]
                               ["three" ["#765843" "#765843" "#765843" "#765843"]]])
    (def multiple-cljs-palettes (parse-palettes multiple-js-palettes))
    (is (= multiple-cljs-palettes
            {:one [[0x76 0x58 0x43] [0x76 0x58 0x43] 
                   [0x76 0x58 0x43] [0x76 0x58 0x43]]
             :two [[0x76 0x58 0x43] [0x76 0x58 0x43] 
                   [0x76 0x58 0x43] [0x76 0x58 0x43]]
             :three [[0x76 0x58 0x43] [0x76 0x58 0x43] 
                     [0x76 0x58 0x43] [0x76 0x58 0x43]]})) => true
```