(defproject dash "0.1.0-SNAPSHOT"
  :description "A dashboard powered by Mindfork."
  :url "https://github.com/synapse-garden/dash"
  :license {:name "GNU General Public License Version 3"
            :url "http://www.gnu.org/copyleft/gpl.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.clojure/clojurescript "1.7.28"]
                 [compojure "1.4.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [cheshire "5.5.0"]
                 [figwheel "0.3.9"]
                 [org.omcljs/om "0.9.0"]
                 [cljs-ajax "0.3.14"]
                 [com.cognitect/transit-cljs "0.8.220"]
                 [com.cognitect/transit-clj "0.8.281"]]

  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-figwheel "0.3.9"]
            [lein-ring "0.9.6"]]

  :clean-targets ^{:protect false} ["resources/public/out"
                                    "resources/public/test_out"
                                    "resources/public/script"
                                    "target"]

  :cljsbuild {
    :builds [{:id "release"
              :source-paths ["src"]
              :compiler {
                :output-to "build/public/script/dash.js"
                :output-dir "build/public/out"
                :optimizations :advanced
                :pretty-print false}}

             {:id "dash"
              :source-paths ["src"]
              :figwheel { :on-jsload "dash.fw/on-jsload" }
              :compiler {
                :output-to "resources/public/script/dash.js"
                :output-dir "resources/public/out"
                :optimizations :none
                :cache-analysis true
                :pretty-print true
                :source-map true}}

             {:id "test"
              :source-paths ["src" "test"]
              :figwheel { :on-jsload "dash-test.fw/on-jsload" }
              :compiler {
                :output-to "resources/public/script/tests.js"
                :output-dir "resources/public/test_out"
                :optimizations :none
                :cache-analysis true
                :pretty-print true
                :source-map true}}]}

  :figwheel {
             :server-port 3449
             :ring-handler server.handler/test-app
             :css-dirs ["resources/public/css"]})

; http://swannodette.github.io/2014/07/26/transit--clojurescript/
