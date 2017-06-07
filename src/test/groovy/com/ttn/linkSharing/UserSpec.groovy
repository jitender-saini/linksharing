package com.ttn.linkSharing

import com.ttn.linksharing.User
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    void "canary"() {
        expect: true
    }

    @Unroll("Testing User Validation #sno #testName")
    void "test user validation"() {
        setup:
        User user = new User(firstName: fname, lastName: lname, email: email, password: password, userName: userName)

        when:
        Boolean result = user.validate(['firstName', 'lastName', 'email', 'password', 'userName'])

        then:
        result == valid

        where:
        sno | testName           | fname | lname   | email          | password   | userName    | valid
        1   | "Blank first name" | ""    | "Saini" | "abc@ttn.com"  | "password" | "username1" | false
        2   | "Null first name"  | null  | "Saini" | "ab1@ttn.com"  | "password" | "username2" | false
        3   | "Blank last name"  | "Jay" | ""      | "ab2@ttn.com"  | "password" | "username3" | false
        4   | "Null last name"   | "Jay" | null    | "ab3@ttn.com"  | "password" | "username4" | false
        5   | "Correct input"    | "Jay" | "Saini" | "jay@ttn.com"  | "password" | "jaysaini"  | true
        6   | "Null email"       | "Jay" | "Saini" | null           | "password" | "username5" | false
        7   | "Blank email"      | "Jay" | "Saini" | ""             | "password" | "username6" | false
        8   | "Null password"    | "Jay" | "Saini" | "jay@ttn.com"  | null       | "jaysaini2" | false
        9   | "Blank password"   | "Jay" | "Saini" | "jay@ttn.com"  | ""         | "jaysaini2" | false
        10  | "Null username"    | "Jay" | "Saini" | "xyz@ttn.com"  | "password" | null        | false
        11  | "Blank username"   | "Jay" | "Saini" | "xy2@ttn.com"  | "password" | ""          | false
        13  | "Email validation" | "jay" | "Saini" | ".com"         | "password" | " username" | false
        14  | "Email validation" | "jay" | "Saini" | "email"        | "password" | " username" | false
        15  | "Email validation" | "jay" | "Saini" | "@ttn"         | "password" | " username" | false
        16  | "Email validation" | "jay" | "Saini" | "@.com"        | "password" | " username" | false
        17  | "Email validation" | "jay" | "Saini" | "fdd@.com"     | "password" | " username" | false
        18  | "Email validation" | "jay" | "Saini" | "fd d@ttn.com" | "password" | " username" | false
        19  | "Email validation" | "jay" | "Saini" | "fdd@ttn.43"   | "password" | " username" | false
    }

    def "Email address of user should be unique"() {
        setup:
        String email = "test@tothenew.com"
        User user = new User(firstName: "fname",
                lastName: "lname",
                email: email,
                password: "password",
                userName: "userName",
                dateCreated: new Date(),
                lastUpdated: new Date())

        when:
        user.save()
        then:
        user.count() == 1

        when:
        User newUser = new User(firstName: "fname",
                lastName: "lname",
                email: email,
                password: "password",
                userName: "userName2",
                dateCreated: new Date(),
                lastUpdated: new Date())
        newUser.save()

        then:
        User.count() == 1
        newUser.errors.allErrors.size() == 1
        newUser.errors.getFieldErrorCount('email') == 1
    }

    def "UserName of user should be unique"() {
        setup:
        String userName = "username"
        User user = new User(firstName: "fname", lastName: "lname", email: "email@ttn.com", password: "password", userName: userName)

        when:
        user.save()
        then:
        User.count() == 1

        when:
        User newUser = new User(firstName: "fname", lastName: "lname", email: "email11@ttn.com", password: "password", userName: userName)
        newUser.save()

        then:
        User.count() == 1
        newUser.errors.allErrors.size() == 1
        newUser.errors.getFieldErrorCount('userName') == 1
    }

    def "toString test"() {

        given:
        User user = new User(userName: userName, email: email, isAdmin: isAdmin)

        when:
        String userString= user.toString()
        then:
        userString == result

        where:
        userName   | email              | isAdmin | result
        "jaysaini" | "jay@tothenew.com" | true    | "User -> userName: jaysaini isAdmin: true  email: jay@tothenew.com"
    }
}
