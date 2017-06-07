package com.ttn.linksharing

class LinkResource extends Resource {

    String url

    static constraints = {
        url nullable: false, blank: false, url: true
    }

    @Override
    String toString() {
        return "Link Resource-> ${url}"
    }
}
