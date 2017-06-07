package com.ttn.linkSharing

import com.ttn.linksharing.DocumentResource
import com.ttn.linksharing.Resource
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DocumentResource)
class DocumentResourceSpec extends Specification {

    @Unroll("#sno")
    void "validate Document Resource"() {

        setup:
        DocumentResource documentResource = new DocumentResource(filePath: filePath, description: description)

        when:
        Boolean result = documentResource.validate(['description', 'filePath'])

        then:
        result == valid

        where:
        sno | description   | filePath      | valid
        1   | "description" | '/ home/test' | true
        2   | " "           | '/home/test'  | false
        3   | null          | '/home/test'  | false
        4   | "description" | ' '           | false
        5   | "description" | null          | false
    }

    @Unroll("#sno")
    void "toString test"() {

        setup:
        Resource documentResource = new DocumentResource(filePath: filePath)

        when:
        String link = documentResource.toString()

        then:
        link == result

        where:
        sno | filePath                        | result
        1   | "http://www.ttn.com/groovy.pdf" | "Document Resource -> http://www.ttn.com/groovy.pdf"
    }
}
