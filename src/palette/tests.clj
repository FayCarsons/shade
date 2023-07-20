(ns palette.tests
  (:use clojure.test)
  (:require [palette.core :refer [parse-palette
                                  parse-palettes
                                  parse-r-palette
                                  parse-hex-color
                                  parse-uint-color]]))



(deftest test-palette-parsers
  (let [single (parse-palette ["one" ["#765843" "#765843" "#765843" "#765843"]])
        multi (parse-palettes [["one" ["#765843" "#765843" "#765843" "#765843"]]
                               ["two" ["#765843" "#765843" "#765843" "#765843"]]
                               ["three" ["#765843" "#765843" "#765843" "#765843"]]])
        R (parse-r-palette "name = c(\"#001122\" \"#334455\")")]
    (testing "palette parsing"
      (testing "single palettte"
        (is (= single [:one [[0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43]]])))
      (testing "multiple palettes"
        (is (= multi {:one [[0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43]]
                      :two [[0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43]]
                      :three [[0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43]]})))
      (testing "R palette"
        (is (= R [:name [[0x00 0x11 0x22] [0x33 0x44 0x55]]])))
      (testing "hex color parsing"
        (is (= (parse-hex-color "#001122") [0x00 0x11 0x22])))
      (testing "rgb parsing"
        (is (= (parse-uint-color "rgb(0, 17, 34)") [0 17 34]))))))

(run-tests 'palette.tests)

 