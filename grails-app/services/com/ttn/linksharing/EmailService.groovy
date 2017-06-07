package com.ttn.linksharing

import com.ttn.linksharing.dto.EmailDTO
import grails.transaction.Transactional

@Transactional
class EmailService {

    def mailService

    def sendMail(EmailDTO emailDTO) {
        sendMail {
            async true
            to emailDTO.to.tokenize(";")
            subject emailDTO.subject
            if (emailDTO.content) {
                body emailDTO.content
            } else {
                html view: emailDTO.view, model: emailDTO.model
            }
        }
    }


    void sendUnreadResourcesEmail(User user, List<Resource> resourceList) {
        String serverUrl = 'localhost:8080'
        mailService.sendMail {
            multipart true
            to user.email
            subject "Unread Resources - linkSharing"
            html view: "/email/template/unreadResources", model: [user     : user,
                                                                  resource : resourceList,
                                                                  serverUrl: serverUrl]
//          html """<b>You have some unread resources :$htmlData</b>"""
            log.info(" user : $user notified about unread resources")
        }
    }

    //    void sendUnreadResourcesEmail() {
//        List<User> users = User.getAll()
//        users.each { user ->
//            List<ReadingItem> readingItemList = ReadingItem.findAllByUserAndIsRead(user, false)
//            if (readingItemList) {
//                List<Resource> resourceList = readingItemList*.resource
//                sendMailNotification(user, resourceList)
//            }
//        }
//    }

//    static String generateHtmlMessage(List<Resource> resourceList) {
//        StringBuilder builder = new StringBuilder()
//        resourceList.each { resource ->
//            builder.append("<p>Topic : $resource.topic , " +
//                    "Creator : $resource.createdBy , " +
//                    "description : $resource.description</p></br>")
//        }
//        builder.toString()
//    }
}