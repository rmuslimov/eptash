(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [org.clojure/clojurescript "1.9.456"]
                 [com.stuartsierra/component "0.3.1"]
                 [compojure "1.5.0"]
                 [http-kit "2.1.9"]
                 [org.danielsz/system "0.4.0"]
                 [reloaded.repl "0.2.1"]
                 [ring "1.5.0"]
                 [rum "0.10.8"]
                 [hiccup "2.0.0-alpha1"]
                 [hawk "0.2.11"]
                 [cheshire "5.7.0"]
                 [fs "1.3.3"]
                 [com.climate/claypoole "1.1.4"]]
  :plugins [[lein-figwheel "0.5.9"]
            [lein-cljsbuild "1.1.5" :exclusions [[org.clojure/clojure]]]]
  :main ^:skip-aot {{name}}.core
  :source-paths ["src"]
  :cljsbuild {:builds
              [{:id "dev"
                :figwheel true
                :source-paths ["src/ui"]
                :compiler {:main ui.web
                           :asset-path "/out"
                           :output-to "resources/public/{{name}}.js"
                           :output-dir "resources/public/out"
                           :verbose true
                           :source-map-timestamp true}}]}
  :target-path "target/%s"
  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[figwheel-sidecar "0.5.9"]
                                  [com.cemerick/piggieback "0.2.1"]]
                   :repl-options {; for nREPL dev you really need to limit output
                                  :init (set! *print-length* 50)
                                  :nrepl-middleware
                                  [cemerick.piggieback/wrap-cljs-repl]}}
             :uberjar {:aot :all}}
  :figwheel {:css-dirs ["resources/public/css"]}
  :clean-targets ^{:protect false} ["resources/public/out" "target"]
  )
