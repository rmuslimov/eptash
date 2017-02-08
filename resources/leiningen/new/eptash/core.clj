(ns {{name}}.core
    (:gen-class)
    (:require [compojure
               [core :refer :all]
               [route :as route]]
              [hiccup.page :as h :refer [include-css include-js]]))


(defn index
  "Base html"
  [req]
  (h/html5
   {:lang "en"}
   [:head]
   [:body
    [:div#app.box]]
   (include-js "{{name}}.js")
   (include-css "css/main.css")))

(defroutes app-routes
  (GET "/" [] index)
  (route/resources "/")
  (route/not-found "<h1>Page not found</h1>"))
