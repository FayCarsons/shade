(ns palette.tests
  
  (:require [palette.core :refer [parse-palette
                                  parse-palettes

                                  parse-hex-color
                                  parse-uint-color]]
            [clojure.test :refer [deftest testing is run-tests]]))



(deftest test-palette-parsers
  (let [single (parse-palette ["one" ["#765843" "#765843" "#765843" "#765843"]])
        multi (parse-palettes [["one" ["#765843" "#765843" "#765843" "#765843"]]
                               ["two" ["#765843" "#765843" "#765843" "#765843"]]
                               ["three" ["#765843" "#765843" "#765843" "#765843"]]])]
    (testing "palette parsing"
      (testing "single palettte"
        (is (= single [:one [[0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43]]])))
      (testing "multiple palettes"
        (is (= multi {:one [[0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43]]
                      :two [[0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43]]
                      :three [[0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43] [0x76 0x58 0x43]]})))
      (testing "hex color parsing"
        (is (= (parse-hex-color "#001122") [0x00 0x11 0x22])))
      (testing "rgb parsing"
        (is (= (parse-uint-color "rgb(0, 17, 34)") [0 17 34]))))))

(run-tests 'palette.tests)

 