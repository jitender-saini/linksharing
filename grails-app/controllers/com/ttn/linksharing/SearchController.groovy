package com.ttn.linksharing

import com.ttn.linksharing.co.ResourceSearchCO
import com.ttn.linksharing.co.TopicSearchCO
import com.ttn.linksharing.vo.TopicVO

class SearchController {
    TopicService topicService
    ResourceService resourceService

    def show(String q) {
        if (q) {
            User user = session.user
            TopicSearchCO topicSearchCO = new TopicSearchCO()
            bindData(topicSearchCO, params)

            ResourceSearchCO resourceSearchCO = new ResourceSearchCO()
            bindData(resourceSearchCO, params)

            List<TopicVO> topics = topicService.search(user, topicSearchCO)
            List<Resource> posts = resourceService.search(user, resourceSearchCO)
            render view: "show", model: [topics: topics, posts: posts]
        } else {
            flash.message = "Enter any query to search"
            redirect(controller: "login", action: "index")
        }
    }
}
