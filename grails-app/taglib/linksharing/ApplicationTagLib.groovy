package linksharing

import com.ttn.linksharing.LinkResource
import com.ttn.linksharing.ReadingItem
import com.ttn.linksharing.Resource
import com.ttn.linksharing.Subscription
import com.ttn.linksharing.Topic
import com.ttn.linksharing.User
import com.ttn.linksharing.vo.RatingInfoVO
import com.ttn.linksharing.vo.TopicVO

class ApplicationTagLib {
    static defaultEncodeAs = "raw"

    static namespace = "ls"
//    def isRead = { attrs, body ->
//        User user = session.user
//        if (user) {
//            Resource resource = attrs.resource
//            if (resource) {
//                Integer count = ReadingItem.countByResourceAndIsReadAndUser(resource, true, user)
//                if (count) {
//                    out << "<a href='${createLink(controller: 'readingItem', action: 'toggleIsRead', id: resource.id)}' id='unReadItem'>Mark as Un Read</a> "
//                } else {
//                    out << "<a href='${createLink(controller: 'readingItem', action: 'toggleIsRead', id: resource.id)}' id='readItem'>Mark as Read</a> "
//                }
//            }
//        }
//    }

    def isRead = { attrs, body ->
        User user = session.user
        if (user) {
            Resource resource = attrs.resource
            if (resource) {
                Integer count = ReadingItem.countByResourceAndIsReadAndUser(resource, true, user)
                if (count) {
                    out << "<a onClick='unReadItem(${resource.id})' >Mark as Un Read</a>"
                } else {
                    out << "<a onClick='readItem(${resource.id})' >Mark as Read</a>"
                }
            }
        }
    }

    def isLoggedIn = { attrs, body ->
        if (session.user) {
            out << body()
        }

    }

    def isAdminLoggedIn = { attrs, body ->
        if (session.user.isAdmin) {
            out << body()
        }

    }

    def checkResourceType = { attrs ->
        if (session.user) {
            Resource resource = Resource.get(attrs.resourceId)
            if (resource instanceof LinkResource) {
                out << "<span><a href='${resource.url}' target='_blank'>View Full Site</a></span> "
            } else {
                out << "<span><a href='${createLink(controller: 'resource', action: 'download', params: [filePath: resource.filePath])}'>Download</a></span> "
            }
        } else {
            out << ""
        }
    }

    def showTrendingTopic = {
        User user = session.user
        if (user) {
            List<TopicVO> trendingTopics = Topic.getTrendingTopics()
            out << g.render(template: "/topic/template/trendingTopic", model: [trendingTopics: trendingTopics, user: user])
        }
    }

//    def showTopPost = {
//        User user = user.session
//        List<Resource> topPost = Topic.getTopPost()
//        out << g.render(template: "/resource/template/show", model: [topPost: topPost, user: user])
//    }

    def getSubscriptionCount = { attrs ->
        int count = 0
        if (attrs.topicId) {
            count = Topic.getSubscriptionCount(Topic.read(attrs.topicId))
        } else if (attrs.user) {
            count = User.getSubscriptionCount(attrs.user)
        }
        out << count
    }

    def getResourceCount = { attrs ->
        int count = 0
        if (attrs.topicId) {
            count = Topic.getResourceCount(Topic.read(attrs.topicId))
        }
        out << count
    }

    def showSubscribe = { attrs ->
        User user = session.user
        if (user && attrs.topicId) {
            if (User.isSubscribed(user, attrs.topicId)) {
                out << "<a onClick='deleteSubscribe(${attrs.topicId})' class='unsubscribe'>UnSubscribe</a>"
            } else {
                out << "<a onClick='subscribeTopic(${attrs.topicId})' class='subscribe'>Subscribe</a>"
            }
        }
    }

//    def showSubscribe = { attrs ->
//        User user = session.user
//        if (user && attrs.topicId) {
//            if (User.isSubscribed(user, attrs.topicId)) {
//                out << "<a href='${createLink(controller: 'subscription', action: 'delete', params: [topicId: attrs.topicId])}'>Unsubscribe</a>"
//            } else {
//                out << "<a href='${createLink(controller: 'subscription', action: 'save', params: [topicId: attrs.topicId])}'>subscribe</a>"
//            }
//        }
//    }

    def topicCount = { attrs ->
        int count
        User user = attrs.user
        if (user) {
            count = User.getTopicCount(user)
            out << count
        }
    }

    def isAdminOrCreatorOfResource = { attrs, body ->
        User user = session.user
        Resource resource = Resource.read(attrs.resourceId)
        if (user.isAdmin || resource.createdBy == user) {
            out << body()
//            out << "<a href='javascript:void(0)'  onclick='deleteResource(${attrs.resourceId})'>Delete&nbsp&nbsp&nbsp</a>"
//            out << "<a href='javascript:void(0)' onclick='editResource(${attrs.resourceId})' > Edit</a>"
        } else {
            out << ""
        }
    }

    def isAdminOrCreatorOfTopic = { attrs, body ->
        User user = session.user
        if (user) {
            Topic topic = Topic.findByCreatedByAndId(user, attrs.topicId)
            if (user.isAdmin || topic) {
                out << body()
            }
        } else {
            out << ""
        }
    }

    def notCreatorOfTopic = { attrs, body ->
        User user = session.user
        if (user) {
            Topic topic = Topic.read(attrs.topicId)
            if (user.userName != topic.createdBy.userName) {
                out << body()
            } /*else {
                out << ""
            }*/
        }
    }

    def showChangeSeriousness = { attrs ->
        User user = session.user
        if (user) {
            Subscription subscription = User.getSubscriptionOfTopic(user, attrs.topicId)
            List seriousness = ['Serious', 'Very Serious', 'Casual']
            if (subscription) {
                out << g.render(template: "/topic/template/change-seriousness", model: [subscription: subscription, seriousness: seriousness])
            }
        }
    }

    def showUserDetails = { attrs ->
        User user = User.get(attrs.userId)
        if (user) {
            out << g.render(template: "/user/template/show", model: [user: user])
        }
    }

    def userImage = { attr ->
        if (attr.userId) {
            out << "<img src='${createLink(controller: "login", action: "image", id: "${attr.userId}")}' width=80 height=80 >"
        }
    }

    def userProfileImage = { attr ->
        if (attr.userId) {
            out << "<img src='${createLink(controller: "login", action: "image", id: "${attr.userId}")}' width=130 height=130 >"
        }
    }

    def isSubscribed = { attrs, body ->
        Resource resource = Resource.get(attrs.resourceId)
        User user = session.user
        boolean status = User.isSubscribed(user, resource.topicId)
        if (status || (user && user.isAdmin)) {
            out << body()
        } else {
            out << ""
        }
    }

    def subscribedTopicList = { attrs ->
        User user = session.user
        if (user) {
            List<Topic> list = User.getSubscribedTopic(user)
            out << "${select(from: list, name: "topicId", optionKey: "id", optionValue: "name", id: "topic")}"
        }
    }

    def resourceRating = { attrs ->
        Resource resource = Resource.read(attrs.resourceId)
        RatingInfoVO ratingInfoVo = Resource.getRatingInfo(resource)

        StringBuilder builder = new StringBuilder()
        for (Integer i = 1; i <= 5; i++) {
            if (ratingInfoVo.averageScore >= i) {
                builder.append("<span class='fa fa-star'></span>")
            } else if (ratingInfoVo.averageScore > i && ratingInfoVo.averageScore < i + 1) {
                builder.append("<span class='fa fa-star-half-empty'></span>")

            } else {
                builder.append("<span class='fa fa-star-o'></span>")
            }
        }
        out << builder.toString()
    }

    def isSubscribedToTopic = { attrs, body ->
        Topic topic = Topic.get(attrs.topicId)
        User user = session.user
        if (Subscription.countByTopicAndUser(topic, user)) {
            out << body()
        } else {
            out << ""
        }
    }
}


