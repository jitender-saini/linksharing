package com.ttn.linksharing.co

import grails.validation.Validateable

class UserSearchCO extends SearchCO implements Validateable {
    Boolean isActive

    static constraints = {
        isActive nullable: true
    }
}
