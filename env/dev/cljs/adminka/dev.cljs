(ns ^:figwheel-no-load adminka.dev
  (:require
    [adminka.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
