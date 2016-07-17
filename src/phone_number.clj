(ns phone-number
  (:require [clojure.string :as str]))

(defn- remove-trunk-prefix
  [s]
  (if (= 11 (count s))
    (if (= \1 (first s))
      (subs s 1)
      s)
    s))

(defn number
  "Format phone numbers for SMS messaging."
  [n]
  (let [result (-> n
                   (str/replace " " "")
                   (str/replace "." "")
                   (str/replace "(" "")
                   (str/replace ")" "")
                   (str/replace "-" "")
                   remove-trunk-prefix)]
    (if (= 10 (count result))
      result
      "0000000000")))

(defn area-code
  "Extract the area code from a phone number."
  [n]
  (-> (phone-number/number n)
      (.substring 0 3)))

(defn pretty-print
  "Format a phone number."
  [n]
  (let [sms (phone-number/number n)]
    (str "(" (area-code n) ") " (subs sms 3 6) "-" (subs sms 6))))
