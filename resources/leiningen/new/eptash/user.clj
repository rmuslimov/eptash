(ns user
  (:require [com.stuartsierra.component :as component]
            [{{name}}.core :refer [app-routes]]
            [clojure.java.shell :as shell]
            reloaded.repl
            [system.components.http-kit :refer [new-web-server]]
            [hawk.core :as hawk]))

(defn- run-less-compilation []
  (shell/sh
   "lessc" "resources/public/less/main.less" "resources/public/css/main.css"))

(defrecord LesscServer [path]
  component/Lifecycle
  (start [{path :path}]
    (println (format "Watching %s ..." path))
    (hawk/watch! [{:paths [path]
                   :filter hawk/file?
                   :handler (fn [ctx e] (run-less-compilation))}]))
  (stop [this]
    (hawk/stop! this)))

(defn new-lessc-server [path]
  (LesscServer. path))

(defn dev-system []
  (component/system-map
   :http (new-web-server 8010 app-routes)
   :lessc (new-lessc-server "resources/public/less")))

(reloaded.repl/set-init! #'dev-system)
