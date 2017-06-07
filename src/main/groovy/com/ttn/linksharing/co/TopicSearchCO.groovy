package com.ttn.linksharing.co

import com.ttn.linksharing.User
import com.ttn.linksharing.enums.Visibility

class TopicSearchCO extends SearchCO {
    Long id
    Visibility visibility

    User getUser() {
        User user = User.get(id)
        return user
    }
}