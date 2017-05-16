(ns braid.client.gateway.create-group.subs
  (:require
    [re-frame.core :refer [reg-sub]]
    [braid.client.gateway.helpers :as helpers]))

(reg-sub
  :gateway.action.create-group/disabled?
  (fn [state _]
    (get-in state [:create-group :disabled?])))

(reg-sub
  :gateway.action.create-group/sending?
  (fn [state _]
    (get-in state [:create-group :sending?])))

(reg-sub
  :gateway.action.create-group/error
  (fn [state _]
    (get-in state [:create-group :error])))

(helpers/reg-form-subs :gateway.create-group :create-group)
