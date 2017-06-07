package com.ttn.linksharing

import grails.converters.JSON

class ReadingItemController {

    def toggleIsRead(Long id) {
        Map json = [:]
        User user = session.user
        Resource resource = Resource.read(id)
        if (user && resource) {
            ReadingItem readingItem = ReadingItem.findByResourceAndUser(resource, user)
            if (readingItem) {
                readingItem.isRead = !readingItem.isRead
            } else {
                readingItem = new ReadingItem(resource: resource, user: user, isRead: true)
            }
            if (readingItem.save(flush: true)) {
                json.success = "Status updated"
            } else {
                json.error = readingItem.errors.allErrors.collect { message(error: it) }.join('<br/>')
            }
//            redirect(controller: 'user', action: 'index', id: resource.topic.id)
        } else {
//            redirect(controller: 'user', action: 'index', id: resource.topic.id)
            render json as JSON
        }
    }

    def readItem(Long resourceId) {
        Map json = [:]
        User user = session.user
        Resource resource = Resource.read(resourceId)
        ReadingItem readingItem = ReadingItem.findByResourceAndUser(resource, user)
        if (readingItem) {
            readingItem.isRead = true
            readingItem.save(flush: true)
            if (readingItem.hasErrors()) {
                json.error = "Mask as read failed!!"
            } else {
                json.success = "Post marked as read success"
            }
        } else {
            readingItem = new ReadingItem(resource: resource, user: user, isRead: true)
            readingItem.save(flush: true)
            if (readingItem.hasErrors()) {
                json.error = "Mask as read failed!!"
            } else {
                json.success = "Post marked as read success"
            }
        }
        render json as JSON
    }

    def unReadItem(Long resourceId) {
        Map json = [:]
        User user = session.user
        Resource resource = Resource.read(resourceId)
        println("**************************************************88")
        ReadingItem readingItem = ReadingItem.findByResourceAndUser(resource, user)
        if (readingItem) {
            readingItem.isRead = false
            readingItem.save(flush: true)
            if (readingItem.hasErrors()) {
                json.error = "Mask as unRead failed!!"
            } else {
                json.success = "Post marked as unRead success"
            }
        } else {
            readingItem = new ReadingItem(resource: resource, user: user, isRead: true)
            readingItem.save(flush: true)
            if (readingItem.hasErrors()) {
                json.error = "Mask as unRead failed!!"
            } else {
                json.success = "Post marked as unRead success"
            }
        }
        render json as JSON
    }


}