(ns braid.client.gateway.events
  (:require
    [re-frame.core :refer [reg-event-db reg-event-fx dispatch]]
    [braid.client.gateway.fx]
    [braid.client.gateway.user-auth.events]
    [braid.client.gateway.create-group.events]
    [braid.client.gateway.log-in.events]
    [braid.client.gateway.log-in-external.events]))

(reg-event-fx
  :gateway/initialize
  (fn [{state :db} [_ action]]
    {:db (assoc state :action action)
     :dispatch [:gateway/handle-action]}))

(reg-event-fx
  :gateway/handle-action
  (fn [{state :db} _ ]
    (case (state :action)
      :create-group
      {:dispatch [:gateway.action.create-group/initialize]}

      :log-in-external
      {:dispatch [:gateway.action.log-in-external/initialize]}

      :log-in
      {:dispatch [:gateway.action.log-in/initialize]})))

(reg-event-fx
  :gateway/change-user-status
  (fn [{state :db} [_ status]]
    (case (state :action)
      :create-group
      {:dispatch [:gateway.action.create-group/change-user-status status]}

      :log-in-external
      {:dispatch [:gateway.action.log-in-external/change-user-status status]}

      :log-in
      {:dispatch [:gateway.action.log-in/change-user-status status]})))

