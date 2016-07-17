(ns phone-number
  (:require [clojure.string :as str]))

(defn number
  "Format phone numbers for SMS messaging."
  [n]
  (let [remove-trunk (fn [x] (if (and (= 11 (count x))
                                      (= \1 (first x)))
                               (subs x 1)
                               x))]
    (let [result (->> (remove #((set " .()-") %) n)
                      (apply str)
                      remove-trunk)]
      (if (= 10 (count result))
        result
        "0000000000"))))

(defn area-code
  "Extract the area code from a phone number."
  [n]
  (subs (phone-number/number n) 0 3))

(defn pretty-print
  "Format a phone number."
  [n]
  (let [sms (phone-number/number n)]
    (str "(" (area-code n) ") " (subs sms 3 6) "-" (subs sms 6))))
