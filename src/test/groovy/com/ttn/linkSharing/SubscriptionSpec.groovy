package com.ttn.linkSharing

import com.ttn.linksharing.enums.Seriousness
import com.ttn.linksharing.Subscription
import com.ttn.linksharing.Topic
import com.ttn.linksharing.User
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Subscription)
class SubscriptionSpec extends Specification {

    def "validatingSubscription"() {
        given:
        Subscription subscription = new Subscription(topic: topic, user: user, seriousness: seriousness)

        when:
        Boolean valid = subscription.validate()

        then:
        valid == result

        where:
        topic       | user       | seriousness         | result
        new Topic() | new User() | Seriousness.CASUAL  | true
        null        | new User() | Seriousness.SERIOUS | false
        new Topic() | null       | Seriousness.CASUAL  | false
        new Topic() | new User() | null                | false
    }


    def "validating Duplicate Subscription"() {

        given:
        Topic topic = new Topic()
        User user = new User()
        Subscription subscription = new Subscription(topic: topic, user: user, seriousness: Seriousness.SERIOUS,
                dateCreated: new Date(), lastUpdated: new Date())

        when:
        subscription.save()

        then:
        Subscription.count() == 1

        when:
        Subscription duplicateSubscription = new Subscription(topic: topic, user: user, seriousness: Seriousness.SERIOUS,
                dateCreated: new Date(), lastUpdated: new Date())
        duplicateSubscription.save()

        then:
        Subscription.count() == 1
        duplicateSubscription.errors.allErrors.size() == 1
    }

    def "toStringCheck"() {

        given:
        User user = new User(userName: userName)
        Topic topic = new Topic(topicName: topicName)
        Subscription subscription = new Subscription(topic: topic, user: user, seriousness: Seriousness.CASUAL)

        when:
        result == subscription.toString()

        then:
        noExceptionThrown()

        where:
        userName   | topicName | result
        "jaysaini" | "groovy"  | "jaysaini subscribed groovy"
    }
}
