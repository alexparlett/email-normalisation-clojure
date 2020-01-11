(ns email-normalisation.core-test
  (:require [clojure.test :refer :all]
            [email-normalisation.core :refer :all]))

(deftest google-normalisation-test
  (testing "replaces googlemail"
    (is (= "foo@gmail.com" (normalise-email "foo@googlemail.com"))))
  
  (testing "replaces google"
    (is (= "foo@gmail.com" (normalise-email "foo@google.com"))))
  
  (testing "remove dots"
    (is (= "foobar@gmail.com" (normalise-email "foo.bar@gmail.com"))))
  
  (testing "takes only before plus"
    (is (= "foo@gmail.com" (normalise-email "foo+bar@gmail.com")))))

(deftest microsoft-normalisation-test
  (testing "retains domain"
    (is (= "foo@live.co.uk" (normalise-email "foo@live.co.uk"))))

  (testing "takes only before plus"
    (is (= "foo@live.co.uk" (normalise-email "foo+bar@live.co.uk")))))

(deftest fastmail-normalisation-test
  (testing "retains domain"
    (is (= "foo@fastmail.com" (normalise-email "foo@fastmail.com"))))

  (testing "takes only before plus"
    (is (= "foo@fastmail.com" (normalise-email "foo+bar@fastmail.com")))))

(deftest zoho-normalisation-test
  (testing "retains domain"
    (is (= "foo@zohocorp.com" (normalise-email "foo@zohocorp.com"))))

  (testing "takes only before plus"
    (is (= "foo@zohocorp.com" (normalise-email "foo+bar@zohocorp.com")))))

(deftest protonmail-normalisation-test
  (testing "retains domain"
    (is (= "foo@protonmail.com" (normalise-email "foo@protonmail.com"))))

  (testing "takes only before plus"
    (is (= "foo@protonmail.com" (normalise-email "foo+bar@protonmail.com")))))

(deftest rackspace-normalisation-test
  (testing "retains domain"
    (is (= "foo@rackspace.com" (normalise-email "foo@rackspace.com"))))

  (testing "takes only before plus"
    (is (= "foo@rackspace.com" (normalise-email "foo+bar@rackspace.com")))))

(deftest runbox-normalisation-test
  (testing "retains domain"
    (is (= "foo@runbox.com" (normalise-email "foo@runbox.com"))))

  (testing "takes only before plus"
    (is (= "foo@runbox.com" (normalise-email "foo+bar@runbox.com")))))

(deftest icloud-normalisation-test
  (testing "retains domain"
    (is (= "foo@icloud.com" (normalise-email "foo@icloud.com"))))

  (testing "takes only before plus"
    (is (= "foo@icloud.com" (normalise-email "foo+bar@icloud.com")))))

(deftest yahoo-normalisation-test
  (testing "retains domain"
    (is (= "foo@yahoo.com" (normalise-email "foo@yahoo.com"))))

  (testing "takes only before hyphen"
    (is (= "foo@yahoo.com" (normalise-email "foo-bar@yahoo.com")))))

(deftest unknown-normalisation-test
  (testing "retains domain"
    (is (= "foo@example" (normalise-email "foo@example"))))

  (testing "takes only before hyphen"
    (is (= "foo@example" (normalise-email "foo-bar@example"))))
  
  (testing "takes only before plus"
    (is (= "foo@example" (normalise-email "foo+bar@example"))))
  
  (testing "takes only before equals"
    (is (= "foo@example" (normalise-email "foo=bar@example"))))
  
  (testing "takes only before hash"
    (is (= "foo@example" (normalise-email "foo#bar@example")))))

