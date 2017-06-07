package com.ttn.linksharing.vo

import com.ttn.linksharing.Topic
import com.ttn.linksharing.User

class ResourceVO {
    Long id
    String description
    User createdBy
    Topic topic
    String filePath
    String url
    boolean isLinkResource
}
