package com.ttn.linksharing.co

import grails.validation.Validateable

class UserCO implements Validateable {
    String userName
    String firstName
    String lastName
    String password
    String confirmPassword
    String email
    byte[] profilePic
    Boolean isAdmin = false
    Boolean isActive = true
    Date dateCreated
    Date lastUpdated


    static constraints = {
        userName nullable: false, blank: false, unique: true, size: 3..20
        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
        password nullable: false, blank: false, size: 4..20, validator: { val, user ->
            user.confirmPassword == val
        }
        confirmPassword nullable: true, blank: true
        email nullable: false, blank: false, unique: true, email: true
        profilePic nullable: true, blank: true
    }
}
