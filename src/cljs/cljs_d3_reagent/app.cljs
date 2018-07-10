(ns cljs-d3-reagent.app
  (:require [reagent.core :as r]
            [d3-selection :as d3]
            [cljs-d3-reagent.viz :refer-macros [defviz]]))

(defn join-data* [root {:keys [data] :as config}]
  (let [$ (-> root d3/select
              (.select "svg")
              (.attr "width" (config :width))
              (.attr "height" (config :height))
              (.selectAll "circle")
              (.data data))
        _ (-> $ .enter
              (.append "circle")
              (.attr "cy" 60)
              (.attr "r" 25)
              (.merge $)
              (.attr "cx" #(* % 30)))
        _ (-> $ .exit .remove)]))

(defviz v
  join-data*
  [state]
  [:div {:style {:text-align "center"}}
   [:svg {:width (:width state)}]])

(defn init []
  (r/render-component
    [v {:width 300
        :height 120
        :data #js [1 2 3]}]
    (.getElementById js/document "container")))
