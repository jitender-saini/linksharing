package com.ttn.linksharing

import grails.converters.JSON
import org.springframework.web.multipart.MultipartFile

class ResourceController {

    def index() {

    }

    def saveLink() {
        Resource linkResource = new LinkResource(url: params.link, description: params.description,
                createdBy: session.user, topic: Topic.load(params.topicId))
        linkResource.save(flush: true)
        if (linkResource.hasErrors()) {
            log.info linkResource.errors.allErrors
            redirect(controller: "user", action: "index")
        } else {
            flash.message = "save success link"
            redirect(controller: "user", action: "index")
        }
    }

    def saveDoc() {
        try {
            MultipartFile file = params.file
            String extension = '.' + file.originalFilename.tokenize('.').last()
            String folderPath = grailsApplication.config.resource.document.folderPath
            File directory = new File(folderPath)
            String fullPath = folderPath + UUID.randomUUID() + extension
            if (!directory.exists()) {
                directory.mkdirs()
            }
            if (file.empty) {
                flash.message = "File can't be empty"
            } else {
                DocumentResource documentResource = new DocumentResource(filePath: fullPath, description: params.description,
                        topic: Topic.load(params.topicId), createdBy: session.user)
                documentResource.save(flush: true)
                if (documentResource.hasErrors()) {
                    log.info documentResource.errors.allErrors
                } else {
                    flash.message = " saved success"
                }
                file.transferTo(new File(fullPath))
            }
            redirect(controller: 'user', action: 'index')
        } catch (Exception e) {
            redirect(controller: 'user', action: 'index')
        }
    }

    def show(Long resourceId) {
        Resource resource = Resource.get(resourceId)
        if (resource) {
            render(view: "resourceShow", model: [resource: resource])
        } else {
            flash.message = "Post not available"
        }
    }

    def editResource(String description, Long resourceId) {
        log.info(" $description $resourceId")
        Resource resource = Resource.get(resourceId)
        if (resource && description) {
            resource.description = description
            resource.save(flush: true, failOnError: true)
            if (resource.hasErrors()) {
                flash.error = "Error in updating Resource "
            } else {
                flash.message = "Resource Updated"
            }
        }
        redirect(controller: 'user', action: 'index')
    }

    def delete(Long resourceId) {
        Map json = [:]
        Resource resource = Resource.read(resourceId)
        if (resource) {
            resource.delete(flush: true, failOnError: true)
            json.success = "Resource Deleted!!"
//            redirect(controller: "user", action: "index")
        } else {
            json.error = "Resource Deletion failed!!"
//            redirect(controller: "user", action: "index")
        }
        render json as JSON
    }

    def rating(String resourceId, Long rating) {
        User user = session.user
        Resource resource = Resource.read(resourceId)
        ResourceRating resourceRating = ResourceRating.findByCreatedByAndResource(user, resource)
        if (resourceRating) {
            resourceRating.score = rating
        } else {
            resourceRating = new ResourceRating(createdBy: user, resource: resource, score: rating)
        }
        resourceRating.save(flush: true, failOnError: true)
        if (resourceRating.hasErrors()) {
            flash.error = "error in updating ratings"
        }
        flash.message = "ratings updated"
    }

    def download(String filePath) {
        Map json = [:]
        byte[] bytes = new File(filePath).getBytes()
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1)
        response.setHeader("Content-disposition", "attachment; filename=$fileName")
        response.contentLength = bytes.length
        response.outputStream << bytes
        json.message = "Your download is started Downloaded"
    }
}
