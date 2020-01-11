(ns email-normalisation.providers
  (:require [clojure.string :as string]))

(def providers
  [{:provider "google"
    :mx "^.*google.*\\.com\\.?$"
    :domain-processor (fn [domain] (string/replace domain #"(google|gmail|googlemail).*$" "gmail.com"))
    :username-processor (fn [username] (first (string/split (string/replace username "." "") #"\+")))}
   {:provider "microsoft"
    :mx "^.*outlook.*\\.com\\.?$"
    :domain-processor (partial identity)
    :username-processor (fn [username] (first (string/split username #"\+")))}
   {:provider "fastmail"
    :mx "^.*messagingengine.*\\.com\\.?$"
    :domain-processor (partial identity)
    :username-processor (fn [username] (first (string/split username #"\+")))}
   {:provider "zoho"
    :mx "^.*zohocorp.*\\.com\\.?$"
    :domain-processor (partial identity)
    :username-processor (fn [username] (first (string/split username #"\+")))}
   {:provider "protonmail"
    :mx "^.*protonmail.*\\.ch\\.?$"
    :domain-processor (partial identity)
    :username-processor (fn [username] (first (string/split username #"\+")))}
   {:provider "rackspace"
    :mx "^.*mailcontrol.*\\.com\\.?$"
    :domain-processor (partial identity)
    :username-processor (fn [username] (first (string/split username #"\+")))}
   {:provider "runbox"
    :mx "^.*runbox.*\\.com\\.?$"
    :domain-processor (partial identity)
    :username-processor (fn [username] (first (string/split username #"\+")))}
   {:provider "icloud"
    :mx "^.*icloud.*\\.com\\.?$"
    :domain-processor (partial identity)
    :username-processor (fn [username] (first (string/split username #"\+")))}
   {:provider "yahoo"
    :mx "^.*yahoodns.*\\.net\\.?$"
    :domain-processor (partial identity)
    :username-processor (fn [username] (first (string/split username #"\-")))}
   {:provider "unknown"
    :mx ".*"
    :domain-processor (partial identity)
    ;assume a subset of allow characters as various email provider software (which doesnt have a fixed dns and is self hosted)
    ;allows any of these characters to be used for aliasing
    :username-processor (fn [username] (first (string/split username #"\+|\=|\-|\#")))}])

(defn get-email-provider
  "Get an email provider for the mx records associated with the domain"
  [mx-records]
  (let [mx-providers (map #(get % "data") mx-records)]
    (first
     (for [provider providers
           :let [pattern (re-pattern (:mx provider))]
           :when (some #(re-matches pattern %) mx-providers)] provider))))

(defn normalise-for-provider
  "Normalise the email based on the matching provider rules"
  [{:keys [username-processor domain-processor]} username domain]
  (string/join "@" [(username-processor username) (domain-processor domain)]))