package com.ttn.linksharing


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ApplicationInterceptor)
class ApplicationInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test application interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"application")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
