package com.ttn.linksharing

import com.ttn.linksharing.co.TopicSearchCO
import com.ttn.linksharing.vo.TopicVO
import grails.transaction.Transactional

@Transactional
class SubscriptionService {

    List<TopicVO> search(TopicSearchCO topicSearchCO) {
        if (topicSearchCO.id) {
            User user = topicSearchCO.getUser()
            Map params = [:]
            params.max = topicSearchCO.max
            params.offset = topicSearchCO.offset
            return User.getSubscribedTopic(user, params)
        }
    }
}
