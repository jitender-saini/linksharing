package com.ttn.linksharing.vo

import com.ttn.linksharing.User
import com.ttn.linksharing.enums.Visibility

class TopicVO {
    Long id
    int count
    User createdBy
    String name
    Visibility visibility
}
