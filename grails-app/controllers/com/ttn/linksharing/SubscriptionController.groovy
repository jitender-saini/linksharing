package com.ttn.linksharing

import com.ttn.linksharing.enums.Seriousness
import grails.converters.JSON

class SubscriptionController {

    def save(Long topicId) {
        Map json = [:]
        Topic topic = Topic.read(topicId)
        if (topic) {
            Subscription subscribe = new Subscription(topic: topic, user: session.user)
            subscribe.save(flush: true)
            if (subscribe.hasErrors()) {
                json.error = "Subscription failed!!"
            } else {
                json.success = "Subscription Success"
            }
        } else {
            json.error = "Invalid TopicId!!!"
        }
        render json as JSON
    }

    def update(Long topicId, String seriousness) {
        Map json = [:]
        Subscription subscription = Subscription.read(topicId)
        if (subscription) {
            subscription.seriousness = Seriousness.toEnum(seriousness)
            subscription.save(flush: true)
            if (subscription.hasErrors()) {
                json.error = "Subscription update failed"
            } else {
                json.success = "Subscription updated"
            }
        } else {
            json.error = "Subscription notFound"
        }
        render json as JSON
    }

//todo
    def delete(Long topicId) {
        Map json = [:]
        Subscription subscription = Subscription.findByUserAndTopic(session.user, Topic.read(topicId))
        if (subscription) {
            subscription.delete(flush: true)
            if (subscription.hasErrors()) {
                json.error = "Subscription deletion failed"

            } else {
                json.success = "Subscription Deleted!!"
            }
        }
        render json as JSON
    }
}