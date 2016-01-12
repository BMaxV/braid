(ns chat.client.views.group-invite
  (:require [om.core :as om]
            [om.dom :as dom]
            [clojure.string :as string]
            [cljs.core.async :refer [put!]]
            [om-fields.core :refer [input]]
            [chat.client.store :as store]
            [chat.client.dispatcher :refer [dispatch!]])
  (:import [goog.events KeyCodes]))

(defn group-invite-view
  [group-id owner {:keys [on-focus on-blur] :as opts}]
  (reify
    om/IInitState
    (init-state [_]
      {:collapsed? true
       :invitee-email ""})
    om/IRenderState
    (render-state [_ {:keys [collapsed? invitee-email]}]
      (if collapsed?
        (dom/button #js {:className "invite-open"
                         :onClick (fn [_]
                                    (om/set-state! owner :collapsed? false)
                                    (when on-focus (on-focus)))}
          "invite")
        (dom/div #js {:className "invite-form"}
          (dom/input #js {:value invitee-email :type "email" :placeholder "email address"
                           :onChange (fn [e] (om/set-state! owner :invitee-email (.. e -target -value)))})
          (dom/button #js {:className "invite"
                           :disabled (string/blank? invitee-email)
                           :onClick
                           (fn [_]
                             (dispatch! :invite {:group-id group-id
                                                 :invitee-email invitee-email})
                             (om/set-state! owner {:collapsed? true :invitee-email ""})
                             (when on-blur (on-blur)))}
            "invite")
          (dom/button #js {:className "close"
                         :onClick (fn [_]
                                    (om/set-state! owner :collapsed? true)
                                    (when on-blur (on-blur)))}
            "cancel"))))))

