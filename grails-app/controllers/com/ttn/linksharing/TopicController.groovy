package com.ttn.linksharing

import com.ttn.linksharing.co.ResourceSearchCO
import com.ttn.linksharing.co.TopicSearchCO
import com.ttn.linksharing.dto.EmailDTO
import com.ttn.linksharing.enums.Visibility
import com.ttn.linksharing.co.TopicCO
import grails.converters.JSON

class TopicController {
    TopicService topicService
    EmailService emailService

    def index(Long id) {
        Topic topic = Topic.get(id)
        if (topic) {
            Topic topic1 = new Topic()
            topic1.validate()
            List<Resource> resources = Resource.findAllByTopic(topic, [max: 20, sort: 'id', order: 'desc'])
            render view: 'index', model: [topic: topic, resources: resources, topic1: topic1]
        } else {
            flash.error = "Topic not found"
            redirect(controller: "login", action: "index")
        }
    }

    def showTopic(ResourceSearchCO searchCO) {
        Topic topic = Topic.read(searchCO.topicId)
        User user = session.user
        if (topic) {
            if (topic.visibility == Visibility.PUBLIC) {
                render view: '/topic/showTopic', model: [topic      : topic,
                                                         subscribers: Topic.getSubscribers(topic.id),
                                                         resources  : Topic.getResources(topic.id)]
            } else if (topic.visibility == Visibility.PRIVATE && user) {
                if (topic.createdBy == user || user.isAdmin) {
                    render view: '/topic/showTopic', model: [topic      : topic,
                                                             subscribers: Topic.getSubscribers(topic.id),
                                                             resources  : Topic.getResources(topic.id)]
                }
            } else if (topic.visibility == Visibility.PRIVATE) {
                if (Subscription.findByUserAndTopic(session.user, topic)) {
                    flash.error = "user not subscribed to this topicId"
                    redirect(controller: "login", action: "index")
                } else {
                    flash.error = "Subscription not exist"
                    redirect(controller: "login", action: "index")
                }
            }
        }
    }

    def create(String name, String visibility) {
        if (name && visibility) {
            TopicCO topicCO = new TopicCO(name: name, visibility: Visibility.toEnum(visibility), createdBy: session.user)
            if (topicService.saveTopic(topicCO)) {
                flash.message = "Topic Create Successfully"
            } else {
                flash.message = "Topic Create Failed"
            }
        }
        redirect(controller: "user", action: "index")
    }


    def edit(String topicName, Long topicId) {
        Topic topic = Topic.get(topicId)
        if (topic) {
            topic.name = topicName
            topic.save(flush: true, failOnError: true)
            if (topic.hasErrors()) {
                flash.error = "error in updating topic"
            } else {
                flash.message = "topic name changed"
            }
        } else {
            flash.error = "topic does not exist"
        }
        redirect(controller: 'user', action: 'index')
    }

    def delete(Long topicId) {
        Map json = [:]
        Topic topic = Topic.read(topicId)
        if (topic) {
            topic.delete(flush: true, failOnError: true)
            json.success = "Topic Deleted!!"
        } else {
            json.error = "Topic Deletion failed!!"
        }
        render json as JSON
//        redirect(controller: "user", action: "index")
    }

    def topicList(TopicSearchCO co) {
        co = co ?: new TopicSearchCO([max: 5, offset: 0])
        List<Topic> topics = Topic.search(co).list()
        render(view: 'topicsList', model: [topics: topics, count: Topic.count()])
    }


    def invite(String email, Long topicId) {
        Topic topic = Topic.get(topicId)
        String serverUrl = 'localhost:8080'
        EmailDTO emailDTO = new EmailDTO(to: email,
                subject: "Topic Invitation",
                view: "/email/template/invite",
                model: [topic    : topic,
                        user     : session.user,
                        serverUrl: serverUrl])
        emailService.sendMail(emailDTO)
        flash.message = "Invitation Sent to your friend"
        redirect(controller: "user", action: "index")
    }

    def join(long id) {
        User invitedUser = session.user
        Topic invitedTopic = Topic.read(id)
        if (!User.isSubscribed(invitedUser, invitedTopic.id)) {
            Subscription subscription = new Subscription(topic: invitedTopic, user: invitedUser, seriousness: Seriousness.CASUAL)
            subscription.save(flush: true)
            if (subscription.hasErrors()) {
                flash.error = "Subscription Failed"
            } else {
                flash.message = "Subscribed"
            }
        } else {
            flash.message = "Already Subscribed"
        }
        redirect(controller: "user", action: "index")
    }
}
