(ns cljs-d3-reagent.viz
  (:require [reagent.core]))

(defmacro defviz
  ([name arglist join & body]
   `(defn ~name ~arglist
      (letfn [(join# [$#] (apply (fn ~arglist ~join) (reagent.core/dom-node $#) ~arglist))]
        (reagent.core/create-class
         {:display-name ~(str name)
          :reagent-render (fn ~(-> arglist rest vec) ~@body)
          :component-did-mount join#
          :component-did-update join#})))))
