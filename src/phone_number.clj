(ns phone-number
  (:require [clojure.string :as str]))

(defn number
  "Format phone number for SMS messaging."
  [n]
  (let [result (->> (remove #((set " .()-") %) n)
                    (apply str))]
    (if (or (= 10 (count result))
            (and (= 11 (count result)) (= \1 (first result))))
      (apply str (take-last 10 result))
      "0000000000")))

(defn area-code
  "Extract the area code from a phone number."
  [n]
  (subs (phone-number/number n) 0 3))

(defn pretty-print
  "Format a phone number."
  [n]
  (let [sms (phone-number/number n)]
    (str "(" (area-code n) ") " (subs sms 3 6) "-" (subs sms 6))))
