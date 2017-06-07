package com.ttn.linkSharing

import com.ttn.linksharing.DocumentResource
import com.ttn.linksharing.LinkResource
import com.ttn.linksharing.ReadingItem
import com.ttn.linksharing.Resource
import com.ttn.linksharing.User
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ReadingItem)
class ReadingItemSpec extends Specification {

    @Unroll("#sno")
    void "constraints Of Resource Item Excluding User Uniqueness"() {

        given:
        ReadingItem resourceItem = new ReadingItem(resource: resource, user: user, isRead: isRead)

        when:
        boolean result = resourceItem.validate(['resource', 'user', 'isRead'])

        then:
        result == excepted

        where:
        sno | resource               | user       | isRead | excepted
        1   | Mock(LinkResource)     | Mock(User) | true   | true
        2   | null                   | Mock(User) | true   | false
        3   | Mock(LinkResource)     | null       | true   | false
        4   | Mock(LinkResource)     | Mock(User) | null   | false
        5   | Mock(DocumentResource) | Mock(User) | true   | true
    }

    def "validating Unique Reading Item"() {

        given:
        User user = Mock(User)

        Resource resource = Mock(LinkResource)

        ReadingItem readingItem = new ReadingItem(resource: resource, user: user, isRead: true)
        ReadingItem newReadingItem = new ReadingItem(resource: resource, user: user, isRead: false)

        when:
        readingItem.save()

        then:
        ReadingItem.count() == 1

        when:
        newReadingItem.save()

        then:
        ReadingItem.count() == 1
//        newReadingItem.errors.allErrors.size() == 0
//        newReadingItem.errors.getFieldErrorCount('resource') == 0
    }
}
