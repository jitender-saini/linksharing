package com.ttn.linksharing

import com.ttn.linksharing.enums.Seriousness

class Subscription {

    User user
    Topic topic
    Seriousness seriousness = Seriousness.SERIOUS
    Date dateCreated = new Date()
    Date lastUpdated = new Date()

    static constraints = {
        user unique: "topic", nullable: false, blank: false
    }

    //todo
    static mapping = {
        user lazy: false
        topic lazy: false
    }

    static belongsTo = [topic: Topic]

    String toString() {
        return "User: ${user.userName} Subscribed Topic: ${topic.name}"
    }

}