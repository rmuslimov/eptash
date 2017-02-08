(ns ui.web
  (:require [rum.core :as rum]))

(rum/defc Page []
  [:div "hello from rum!"])

(enable-console-print!)

(rum/mount (Page)
 (js/document.getElementById "app"))
