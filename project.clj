(defproject email-normalisation "1.0.0"
  :description "Email normalisation using dns over https for provider calculations"
  :url "https://github.com/alexparlett/email-normalisation-clojure"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [clj-http "3.10.0"]
                 [org.clojure/data.json "0.2.7"]]
  :repl-options {:init-ns email-normalisation.core})
