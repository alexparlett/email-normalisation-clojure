(ns email-normalisation.core
  (:require [email-normalisation.providers :refer [providers get-email-provider normalise-for-provider]]
            [clj-http.client :as client]
            [clojure.string :as string]
            [clojure.data.json :as json]))

(defn get-mx-records
  "Get the mx records for the domain part of the email"
  [domain]
  (let [response (json/read-str
                  (:body
                   (client/get
                    "https://cloudflare-dns.com/dns-query"
                    {:accept "application/dns-json" :query-params {"type" "MX" "name" domain}})))]
      (or (get response "Answer") (get response "Authority"))
    ))

(defn normalise-email
  "Normalise an email address based on provider from mx record to apply rules"
  [email]
  (let [[username domain] (string/split email #"@") 
        mx-records (get-mx-records domain) 
        email-provider (get-email-provider mx-records)]
    (normalise-for-provider email-provider username domain)))
