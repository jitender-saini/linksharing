package com.ttn.linksharing

import com.ttn.linksharing.co.UpdateProfileCO
import com.ttn.linksharing.co.UserCO
import com.ttn.linksharing.co.UserSearchCO
import com.ttn.linksharing.dto.EmailDTO
import org.apache.commons.lang.RandomStringUtils

class UserController {

    UserService userService
    EmailService emailService

    def index() {
        User user = session.user
        params.max = 5
        params.offset = 0
        render view: '/user/index', model: [subscribedTopic  : User.getSubscribedTopic(user, params),
                                            subscriptionCount: User.getSubscriptionCount(user),
                                            inboxList        : ReadingItem.getUnReadItems(user, params),
                                            unReadCount      : ReadingItem.getUnReadItemCount(user),
                                            trendingTopic    : Topic.getTrendingTopics()]
    }

    def inbox() {
        User user = session.user
        render template: '/user/template/messages', model: [inboxList: ReadingItem.getUnReadItems(user, params)]
    }

    def subscriptions() {
        User user = session.user
        render(template: '/subscription/template/show', model: [subscribedTopic: User.getSubscribedTopic(user, params)])
    }

//    //todo
//    def sendInvitation() {
//        Topic topic = Topic.get(params.topicId)
//        def list = ['user': session.user.fullName, 'topic': topic.name]
//
//        sendMail {
//            to params.email
//            subject "Subscribe ${topic.name}"
//            body(view: "/email/", model: [data: list])
//        }
//        render "email sent ${topic.name}"
//    }

    def forgetPassword(String recoveryEmail) {
        User user = User.findByEmail(recoveryEmail)
        String charset = (('A'..'Z') + ('0'..'9')).join()
        if (user) {
            String newPassword = RandomStringUtils.random(8, charset.toCharArray())
            String serverUrl = 'localhost:8080'
            EmailDTO emailDTO = new EmailDTO(to: recoveryEmail,
                    subject: "Account Recovery",
                    view: "/email/template/password",
                    model: [userName   : user.fullName,
                            newPassword: newPassword,
                            serverUrl  : serverUrl]) //grailsApplication.config.grails.serverURL
            emailService.sendMail(emailDTO)
            user.password = newPassword
            user.save(flush: true)
            flash.message = "Password sent to your email check Your email address!!"
        } else {
            flash.message = "Your email is not valid!!"
        }
        forward(controller: "login", action: "index")
    }

    def profile(Long userId) {
        User user = User.get(userId)
        List<Topic> createdTopics = Topic.findAllByCreatedBy(user)
        [createdTopics    : createdTopics,
         subscribedTopic  : User.getSubscribedTopic(user, params),
         subscriptionCount: User.getSubscriptionCount(user),
         createdPost      : Resource.userResources(user),
         user             : user]
    }


    def register() {
        UserCO userCO = new UserCO()
        bindData(userCO, params)
        boolean result = userService.registration(userCO)
        if (result) {
            flash.success = "User Registration Success"
            User user = User.findByUserName(userCO.userName)
            session.user = user
            forward(controller: "user", action: "index")
        } else {
            flash.error = "User Registration Failed"
            forward(controller: "login", action: "index")
        }
    }

    def updateProfile() {
        User user = session.user
        if (User.findByUserName(params.userName)) {
            flash.message = "UserName already exist please try different userName "
            redirect(controller: "user", action: "editProfile")
        } else {
            UpdateProfileCO profileCO = new UpdateProfileCO()
            bindData(profileCO, params)
            profileCO.profilePic = params.profilePic.getBytes()
            boolean result = userService.updateUser(user, profileCO)
            if (result) {
                flash.success = "User Update Success"
                redirect(controller: "user", action: "index")

            } else {
                flash.error = "User Update Failed"
                redirect(controller: "user", action: "index")
            }
        }
    }

    def updatePassword() {
        User user = session.user
        if (user.password == params.oldPassword) {
            UpdateProfileCO profileCO = new UpdateProfileCO()
            bindData(profileCO, params)
            boolean result = userService.updatePassword(user, profileCO)
            if (result) {
                user.password = params.password
                session.user = user
                flash.success = "Password Update Success"
                redirect(controller: "user", action: "index")
            } else {
                flash.error = "Password Update Failed"
                redirect(controller: "user", action: "index")
            }
        } else {
            flash.message = "Old password is not valid!!"
            redirect(controller: "user", action: "index")
        }
    }

    def editProfile() {
        User user = session.user
        List<Topic> createdTopics = Topic.findAllByCreatedBy(user)
//        List<TopicVO> createdTopics = topicService.search(new TopicSearchCO(id: user.id))
        int createdTopicsCount = Topic.countByCreatedBy(user)
        render view: 'editProfile', model: [createdTopics     : createdTopics,
                                            createdTopicsCount: createdTopicsCount,
                                            user              : user]
    }

    def usersList(UserSearchCO userSearchCO) {
        userSearchCO = userSearchCO ?: new UserSearchCO([max:5, offset:0])
        List<User> users = User.search(userSearchCO).list()
        render(view: 'usersList', model: [users: users,
                                          count: User.count()])
    }

    def toggleActive(Long userId) {
        User user = User.get(userId)
        if (user.isActive) {
            User.executeUpdate("update User set isActive=:active where id=:id", [active: false, id: userId])
        } else {
            User.executeUpdate("update User set isActive=:active where id=:id", [active: true, id: userId])
        }
        redirect(action: 'usersList')
    }

}
