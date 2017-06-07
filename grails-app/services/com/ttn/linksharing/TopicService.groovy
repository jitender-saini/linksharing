package com.ttn.linksharing

import com.ttn.linksharing.co.TopicCO
import com.ttn.linksharing.co.TopicSearchCO
import com.ttn.linksharing.enums.Visibility
import com.ttn.linksharing.vo.TopicVO

//@Transactional
class TopicService {

    def saveTopic(TopicCO topicCO) {
        Topic topic = new Topic(topicCO.properties)
        if (topic.validate()) {
            topic.save()
            return true
        }
        return false
    }

    List<TopicVO> search(User user, TopicSearchCO topicSearchCO) {
        List<TopicVO> topicVOS = []
        if (topicSearchCO.q) {
            if (user) {
                if (user.isAdmin) { //|| user == Topic.read(topicSearchCO.id).createdBy
                    List<Topic> topicList = Topic.createCriteria().list(offset: topicSearchCO.offset, max: topicSearchCO.max) {
                        ilike('name', "%${topicSearchCO.q}%")

                    }
                    topicList.each {
                        topic -> topicVOS.add(new TopicVO(id: topic.id, name: topic.name, visibility: topic.visibility, createdBy: topic.createdBy))
                    }
                } else {
                    List<Topic> topicList = Topic.createCriteria().list(offset: topicSearchCO.offset, max: topicSearchCO.max) {
                        ilike('name', "%${topicSearchCO.q}%")
                        or {
                            eq('visibility', Visibility.PUBLIC)
                            eq('createdBy', user)
                        }
                    }
                    topicList.each {
                        topic -> topicVOS.add(new TopicVO(id: topic.id, name: topic.name, visibility: topic.visibility, createdBy: topic.createdBy))
                    }
                }
            } else {
                List<Topic> topicList = Topic.createCriteria().list(offset: topicSearchCO.offset, max: topicSearchCO.max) {
                    eq('visibility', Visibility.PUBLIC)
                    ilike('name', "%${topicSearchCO.q}%")
                }
                topicList.each {
                    topic -> topicVOS.add(new TopicVO(id: topic.id, name: topic.name, visibility: topic.visibility, createdBy: topic.createdBy))
                }
            }

        }
        return topicVOS
    }

//    List<TopicVO> search(User user, TopicSearchCO topicSearchCO) {
//        List<TopicVO> topicVOS = []
//        if (topicSearchCO.q) {
//            if (user) {
//                if (user.isAdmin ) { //|| user == Topic.read(topicSearchCO.id).createdBy
//                    List<Topic> topicList = Topic.createCriteria().list(offset: topicSearchCO.offset, max: topicSearchCO.max) {
//                        ilike('name', "%${topicSearchCO.q}%")
//
//                    }
//                    topicList.each {
//                        topic -> topicVOS.add(new TopicVO(id: topic.id, name: topic.name, visibility: topic.visibility, createdBy: topic.createdBy))
//                    }
//                }
//            } else {
//                List<Topic> topicList = Topic.createCriteria().list(offset: topicSearchCO.offset, max: topicSearchCO.max) {
//                    eq('visibility', Visibility.PUBLIC)
//                    ilike('name', "%${topicSearchCO.q}%")
//                }
//                topicList.each {
//                    topic -> topicVOS.add(new TopicVO(id: topic.id, name: topic.name, visibility: topic.visibility, createdBy: topic.createdBy))
//                }
//            }
//
//        }
//        return topicVOS
//    }
}

