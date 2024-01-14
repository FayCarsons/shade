# Shade
A collection of color palettes for Clojure/script, derived from various R color palette packages.

All palettes and palette creating tools are exposed in palette.core.clj.
A 'demo' showing all palettes can be viewed by running core/create-demo, starting an HTTP server in the root, and visiting it in the browser.

## Palette Tools

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
