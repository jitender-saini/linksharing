package com.ttn.linksharing.co

import com.ttn.linksharing.User
import com.ttn.linksharing.enums.Visibility
import grails.validation.Validateable

class ResourceSearchCO extends SearchCO implements Validateable {
    Long id
    Long topicId
    Visibility visibility

    User getUser() {
        User user = User.get(id)
        return user
    }
}
