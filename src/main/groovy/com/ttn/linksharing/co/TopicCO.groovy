package com.ttn.linksharing.co

import com.ttn.linksharing.User
import com.ttn.linksharing.enums.Visibility
import grails.validation.Validateable

class TopicCO implements Validateable {
    Long id
    String name
    Visibility visibility
    Integer count
    User createdBy
    static constraints = {
        name nullable: false, blank: false
        visibility nullable: false, blank: false
        createdBy nullable: false, blank: false
        id nullable: true
        count nullable: true
    }
}
