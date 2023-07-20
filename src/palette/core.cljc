(ns palette.core
  (:require [clojure.string :as s]
            [clojure.edn :refer [read-string]]))

(defn parse-hex-color [hex-string]
  (let [to-hex (fn [s]
                 (apply str
                        (cons "0x" s)))
        rgb (rest hex-string)
        r (take 2 rgb)
        g (take 2 (drop 2 rgb))
        b (drop 4 rgb)]
    (mapv (comp read-string to-hex) [r g b])))

(defn parse-uint-color [color-string]
  (let [[_ r g b] (re-find #"[rgb]?\((\d{1,3}),\s*(\d{1,3}),\s*(\d{1,3})\)" color-string)]
    (mapv #(->> %
                (apply str)
                parse-long)
          [r g b])))

(defn parse-palette 
  "parses hex/rgb palete in the format [name [#rrggbb #rrggbb ...]]"
  [[name colors]]
  [(keyword name)
   (mapv #(if (boolean (re-find #"^#" %))
            (parse-hex-color %)
            (parse-uint-color %))
         colors)])

(defn parse-palettes 
  "parses hex RGB palettes in the format 
  [[name [#rrggbb]]
   [name [#rrggbb]]
   [name [#rrggbb]]]"
  [palettes]
  (into {}
        (mapv parse-palette
              palettes)))