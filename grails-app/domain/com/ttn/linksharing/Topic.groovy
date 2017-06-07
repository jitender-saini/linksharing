package com.ttn.linksharing

import com.ttn.linksharing.co.TopicSearchCO
import com.ttn.linksharing.enums.Seriousness
import com.ttn.linksharing.enums.Visibility
import com.ttn.linksharing.co.TopicCO

class Topic {

    String name
    Visibility visibility
    User createdBy
    Date dateCreated
    Date lastUpdated

    static belongsTo = [createdBy: User]

    static hasMany = [subscription: Subscription, resources: Resource]

    static mapping = {
        sort name: "asc"
    }

    static constraints = {
        name unique: 'createdBy', nullable: false, blank: false
        visibility nullable: false, blank: false
        createdBy nullable: false, blank: false
    }


    def afterInsert() {
        withNewSession {
            Subscription subscription = new Subscription(topic: this, user: createdBy, seriousness: Seriousness.VERY_SERIOUS)
            subscription.save()
            if (subscription.hasErrors()) {
                log.error "Subscription failed ${subscription.errors.allErrors}"
            } else {
                log.info "${createdBy.userName} has subscribed ${name}"
            }
        }
    }

    static namedQueries = {
        search { TopicSearchCO co ->
            co.q = co.q ?: ''
            or {
                ilike('name', "%${co.q}%")
            }
            if (co.sort) {
                order(co.sort, co.order)
            }
            if (co.visibility) {
                if (co.visibility == Visibility.PUBLIC) {
                    eq('visibility', Visibility.PUBLIC)
                } else if (co.visibility == Visibility.PRIVATE) {
                    eq('visibility', Visibility.PRIVATE)
                }
            }
            maxResults(co.max)
            firstResult(co.offset)
        }
    }

    static List<TopicCO> getTrendingTopics() {
        def topicVOList = Resource.createCriteria().list(max: 5) {
            projections {
                createAlias('topic', 't')
                groupProperty('t.id')
                property('t.name')
                property('t.visibility')
                property('t.createdBy')
                count('t.id', 'rCount')
            }
            and {
                order('rCount', 'desc')
                order('t.name')
            }
        }.collect {
            new TopicCO(id: it[0], name: it[1], visibility: it[2], createdBy: it[3], count: it[4])
        }
        return topicVOList
    }

    static List<Resource> getTopPost() {
        List posts = ResourceRating.createCriteria().list(max: 5) {
            projections {
                groupProperty('resource.id')
                count('id', 'rCount')
            }
            order('rCount', 'desc')
        }
        return Resource.getAll(posts.collect { it[0] })
    }

    static int getSubscriptionCount(Topic topic) {
        int count = Subscription.countByTopic(topic)
        return count
    }

    static int getResourceCount(Topic topic) {
        int count = Resource.countByTopic(topic)
        return count

    }

    static List<User> getSubscribers(Long id) {
        Topic topic = read(id)
        List<User> subscribers = Subscription.createCriteria().list {
            projections {
                property('user')
            }
            eq('topic', topic)
        }
        return subscribers
    }

    static List<Resource> getResources(Long id) {
        List<Resource> resources = Resource.findAllByTopic(read(id))
        return resources
    }

    String toString() {
        return "Topic: ${name} is createdBy ${createdBy.userName}"
    }
}
