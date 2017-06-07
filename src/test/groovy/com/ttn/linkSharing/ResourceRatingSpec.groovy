package com.ttn.linkSharing

import com.ttn.linksharing.LinkResource
import com.ttn.linksharing.ResourceRating
import com.ttn.linksharing.User
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ResourceRating)
@TestMixin(DomainClassUnitTestMixin)
class ResourceRatingSpec extends Specification {

    void "canary"() {
        expect: true
    }

    @Unroll("#sno")
    void "Check Resource Rating "() {

        setup:
        ResourceRating resourceRating = new ResourceRating(
                resource: resource,
                createdBy: createdBy,
                score: score)

        when:
        Boolean result = resourceRating.validate(['resource', 'createdBy', 'score'])
        resourceRating.save()

        then:
        result == valid

        where:
        sno | resource           | createdBy  | score | valid
        1   | Mock(LinkResource) | Mock(User) | 3     | true
        2   | null               | Mock(User) | 3     | false
        3   | Mock(LinkResource) | null       | 3     | false
        4   | Mock(LinkResource) | Mock(User) | 10    | false
        5   | Mock(LinkResource) | Mock(User) | 0     | false
    }

    def "validate Unique Resource Rating"() {
        setup:
        LinkResource resource = Mock(LinkResource)
        User user = Mock(User)
        ResourceRating resourceRating = new ResourceRating(resource: resource, createdBy: user, score: 3)

        when:
        resourceRating.save()

        then:
        ResourceRating.count()==1
        when:
        ResourceRating newResourceRating = new ResourceRating(resource: resource, createdBy: user, score: 4)
        newResourceRating.save()

        then:
        ResourceRating.count()==1
        newResourceRating.errors.allErrors.size()==1
    }
}
