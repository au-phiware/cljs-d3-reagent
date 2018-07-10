(ns cljs-d3-reagent.app
  (:require [reagent.core :as r]
            [d3-selection :as d3]
            [cljs-d3-reagent.viz :refer-macros [defviz]]))

(defviz v
  [$ {:keys [data width height]}]
  (let [$ (-> $ d3/select
              (.select "svg")
              (.attr "width" width)
              (.selectAll "circle")
              (.data data))
        _ (-> $ .enter
              (.append "circle")
              (.attr "cy" 60)
              (.attr "r" 25)
              (.merge $)
              (.attr "cx" #(* % 30)))
        _ (-> $ .exit .remove)])
  [:div {:style {:text-align "center"}}
   [:svg {:height height}]])

(defn init []
  (r/render-component
    [v {:width 300
        :height 120
        :data #js [1 2 3]}]
    (.getElementById js/document "container")))
