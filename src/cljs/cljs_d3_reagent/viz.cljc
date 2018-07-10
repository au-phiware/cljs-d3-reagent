(ns cljs-d3-reagent.viz
  (:require [reagent.core]))

(defmacro defviz
  ([name join-fn arglist & body]
   `(defn ~name ~arglist
      (letfn [(join# [$#] (apply ~join-fn (reagent.core/dom-node $#) ~arglist))]
        (reagent.core/create-class
         {:display-name ~(str name)
          :reagent-render (fn ~arglist ~@body)
          :component-did-mount join#
          :component-did-update join#})))))
