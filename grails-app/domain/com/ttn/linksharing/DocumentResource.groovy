package com.ttn.linksharing

class DocumentResource extends Resource {

    String filePath

    static constraints = {
        filePath blank: false, nullable: false
    }


    @Override
    String toString() {
        return "Document Resource -> ${filePath}"
    }
}
