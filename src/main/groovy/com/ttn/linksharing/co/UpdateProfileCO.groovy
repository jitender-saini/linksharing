package com.ttn.linksharing.co

import grails.validation.Validateable

class UpdateProfileCO implements Validateable {
    String userName
    String firstName
    String lastName
    byte[] profilePic
    String password
    String confirmPassword

    static constraints = {
        userName nullable: true, blank: true
        firstName nullable: true, blank: true
        lastName nullable: true, blank: true
        profilePic nullable: true, blank: true
        password nullable: true, blank: true, size: 4..20
        confirmPassword nullable: true, blank: true, validator: { val, user ->
            if (user.passowrd) {
                user.password == val
            }
        }
    }
}
