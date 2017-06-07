package com.ttn.linksharing

import com.ttn.linksharing.co.ResourceSearchCO
import com.ttn.linksharing.vo.RatingInfoVO
import com.ttn.linksharing.vo.ResourceVO

abstract class Resource {

    String description
    User createdBy
    Topic topic
    Date dateCreated
    Date lastUpdated

    static belongsTo = [topic: Topic, createdBy: User]
    static hasMany = [readingItem: ReadingItem, resourceRating: ResourceRating]
    static transients = ['ratingInfo']

    static constraints = {
        description nullable: false, blank: false, maxSize: 2048
        createdBy nullable: false, blank: false
        topic nullable: false, blank: false
    }

    static mapping = {
        description type: 'text'
        tablePerHierarchy: false
    }

    static namedQueries = {
        search { ResourceSearchCO searchCo ->
            if (searchCo.topicId) {
                eq('topic_id', searchCo.topicId)
            }
            if (searchCo.visibility) {
                eq('topic.visibility', searchCo.visibility)
            }
            if (searchCo.id) {
                eq('createdBy.id', searchCo.id)
            }
            if (searchCo.q) {
                ilike('description', "%${searchCo.q}%")
            }

            and {
                order('lastUpdated', 'desc')
            }
        }
    }

    static RatingInfoVO getRatingInfo(Resource resource) {
        List result = ResourceRating.createCriteria().get {
            projections {
                count('id', 'totalVotes')
                sum('score')
                avg('score')
            }
            eq('resource', resource)
        }
        return new RatingInfoVO(totalVotes: result[0], totalScore: result[1], averageScore: result[2])
    }

//    static List<Resource> getRecentPost() {
//        List<Resource> result = createCriteria().list(max: 5) {
//            order('dateCreated', 'desc')
//        }
//        return result
//    }

    static List<ResourceVO> getRecentPost() {
        return list(max: 5, sort: "dateCreated", order: "desc")
    }

//    static List<Resource> findResourceByQuery(String query) {
//        List<Resource> result = createCriteria().list() {
//            projectons {
//                createAlias('topic', 't')
//                or {
//                    like('description', query)
//                    like('t.name', query)
//                }
//            }
//        }
//        return result
//    }

    static List<Resource> userResources(User user) {
        List<Resource> post = findAllByCreatedBy(user)
        return post
    }


    String toString() {
        return "Resource for Topic : ${topic.name} created by ${createdBy.userName}"
    }
}
