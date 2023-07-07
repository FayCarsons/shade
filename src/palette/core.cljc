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
  (let [[_ r g b] (re-find #"rgb\((\d{1,3}),\s*(\d{1,3}),\s*(\d{1,3})\)" color-string)]
    (mapv #(->> %
                (apply str)
                parse-long)
          [r g b])))

(defn parse-palette [[name colors]]
  [(keyword name)
   (mapv #(if (boolean (re-find #"^#" %))
            (parse-hex-color %)
            (parse-uint-color %))
         colors)])

(defn parse-palettes 
  "parses hex RGB palettes in the format [[name [#rrggbb]]]"
  [palettes]
  (into {}
        (mapv parse-palette
              palettes)))

(defn parse-r-palette
  "quotations must be escaped
   example format: 
   'name = c('#001122','#334455')'"
  [r-palette]
  (let #?(:cljs [split-s (s/split r-palette " ")
                 name (first split-s)
                 colors (->> split-s
                             (drop 2)
                             (map #(-> %
                                       (s/replace #"," "")
                                       (s/replace #"c\(" "")
                                       (s/replace #"\)" "")))
                             (remove empty?)
                             (into []))]
          :clj [split-s (->> (s/split r-palette #"\(|\'|\s" -1)
                             (map #(-> %
                                       (s/replace #"[,]" "")
                                       (s/replace #"[c\(]" "")
                                       (s/replace #"[\)]" "")))
                             (remove empty?)
                             (into []))
                name (first split-s)
                colors (vec (remove empty? (mapv #(s/replace % #"[=|\"]" "")
                                                 (rest split-s))))])
    [(keyword name) (mapv parse-hex-color colors)]))

 







