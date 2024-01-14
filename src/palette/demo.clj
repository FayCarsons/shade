(ns palette.demo
  (:require [hiccup2.core :refer [html h]]
            [palette.palettes :as p]))

(def root-style 
  "body {
        margin: 0;
        padding: 0;
        padding-left: 0.5em;
        padding-right: 0.5em;
        overflow: scroll;
      }
   
   .colors {
        display: flex;
        flex-direction: row;
   }")

(def pairs
  [["MetBrewer" p/met-palettes]
   ["PNW" p/pnw-palettes]
   ["Wes Anderson" p/wes-anderson-palettes]
   ["MexBrewer" p/mexbrewer]])

(defn show-palette [[title palettes]]
  (print title)
  [:div {:id (str title)
         :class "palette"
         :style "display: none;"}
   [:h2 title]
   [:div
    (map (fn [[palette-name colors]]
           [:div 
            [:h3 (name palette-name)]
            [:div {:class "colors"}
             (map (fn [color]
                    (let [color-str
                          (clojure.string/join ", "
                                               (map str
                                                    color))]
                      [:div {:style
                             (str "background-color: rgb("
                                  color-str
                                  "); height: 150px; width: 100px;")}]))
                  colors)]])
         palettes)]])

(def document
  (html
   [:html
    [:head
     [:title "Shade"]
     [:script {:src "demo.js" :defer true}]
     [:style root-style]]
    [:body
     [:h1 "Shade - A Clojure color palette library"]
     [:p "Currently, all palettes are derived from various R color palette packages. 
          I plan on expanding palettes andpossibly palette-related tools in the future."]
     [:select {:name "palettes"
               :onchange "show_palette()"}
      (map (comp (fn [v] [:option {:value v} v])
                 first)
           pairs)]
     [:hr]
     (map show-palette pairs)]]))

(defn create-demo [] (spit "index.html" document))