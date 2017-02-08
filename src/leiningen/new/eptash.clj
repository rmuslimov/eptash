(ns leiningen.new.eptash
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "eptash"))

(def data {:name "shapdesk"})

(defn eptash
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' eptash project.")
    (->files data
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["src/ui/web.cljs" (render "web.cljs" data)]
             ["project.clj" (render "project.clj" data)]
             ["dev/user.clj" (render "user.clj" data)]
             ["resources/public/less/main.less" (render "main.less" data)]
             [".gitignore" (render ".gitignore" data)]
             ["readme.md" (render "readme.md" data)]
             )))
