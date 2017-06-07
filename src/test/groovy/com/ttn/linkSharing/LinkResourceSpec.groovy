package com.ttn.linkSharing

import com.ttn.linksharing.LinkResource
import com.ttn.linksharing.Resource
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(LinkResource)
class LinkResourceSpec extends Specification {

    @Unroll("#sno")
    void "validate Link Resource"() {

        setup:
        LinkResource linkResource = new LinkResource(url: url, description: description)

        when:
        Boolean result = linkResource.validate(['description', 'url'])

        then:
        result == valid

        where:
        sno | description   | url                        | valid
        1   | "description" | 'http://www.tothenew.com/' | true
        2   | " "           | 'http://www.tothenew.com/' | false
        3   | null          | 'http://www.tothenew.com/' | false
        4   | "description" | ' '                        | false
        5   | "description" | null                       | false
    }

    @Unroll("#sno")
    void "toString test"() {

        setup:
        Resource linkResource = new LinkResource(url: url)

        when:
        String link = linkResource.toString()

        then:
        result == link

        where:
        sno | url                               | result
        1   | "http://www.testLinkResource.com" | "Link Resource-> http://www.testLinkResource.com"
    }
}