package com.ttn.linkSharing

import com.ttn.linksharing.enums.Visibility
import com.ttn.linksharing.Topic
import com.ttn.linksharing.User
import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */

@TestMixin(DomainClassUnitTestMixin)
@Mock([Topic, User])
class TopicSpec extends Specification {

    @Unroll("#sno")
    void "Topic validation test"() {
        setup:
        Topic topic = new Topic(name: topicTitle, description: desc, createdBy: user, visibility: visibility)

        when:
        Boolean result = topic.validate(['name', 'description', 'createdBy', 'visibility'])

        then:
        result == valid

        where:
        sno | topicTitle | desc   | user       | visibility         | valid
        1   | "java"     | "sdsd" | Mock(User) | Visibility.PRIVATE | true
        2   | "java"     | "sdsd" | Mock(User) | Visibility.PUBLIC  | true
        3   | null       | "sdsd" | Mock(User) | Visibility.PRIVATE | false
        4   | ""         | "sdsd" | Mock(User) | Visibility.PRIVATE | false
        5   | "java"     | null   | Mock(User) | Visibility.PRIVATE | false
        6   | "java"     | ""     | Mock(User) | Visibility.PRIVATE | false
        7   | "java"     | "desc" | null       | Visibility.PRIVATE | false
        8   | "java"     | "desc" | null       | Visibility.PRIVATE | false
        9   | "java"     | "desc" | Mock(User) | null               | false
    }

//    To run this method commit afterInsert in Topic Domain
    def "Topic should be unique per user"() {
        setup:
        String topicTitle = "Groovy"
        User user = new User(firstName: "fname", lastName: "lname", email: "email@ttn.com", password: "password", userName: "userName")

        Topic topic = new Topic(name: topicTitle, description: "groovy lang", createdBy: user, visibility: Visibility.PRIVATE)
        when:
        topic.save()

        then:
        Topic.countByNameAndCreatedBy(topicTitle, user) == 1

        when:
        Topic duplicateTopic = new Topic(name: topicTitle, description: "groovy lang", createdBy: user, visibility: Visibility.PRIVATE)
        duplicateTopic.save()

        then:
        Topic.countByNameAndCreatedBy(topicTitle, user) == 1
        duplicateTopic.errors.allErrors.size() == 1
        duplicateTopic.errors.getFieldErrorCount('name') == 1
    }

    def "toString test"() {

        given:
        User user = new User(userName: 'jaysaini')
        Topic topic = new Topic(name: topicTitle, createdBy: user)

        when:
        String topicName = topic.toString()

        then:
        topicName == result

        where:
        topicTitle      | result
        "testTopicName" | "Topic: testTopicName is createdBy jaysaini"
    }
}
