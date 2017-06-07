package com.ttn.linksharing

class ApplicationInterceptor {
    ApplicationInterceptor() {
        matchAll().excludes(controller: 'login')
                .excludes (controller: 'user', action:'profile')
                .excludes(controller: 'search', action: 'show')
                .excludes(controller: 'topic', action: 'showTopic')
                .excludes(controller: 'resource', action: 'show')
    }

    boolean before() {
        if (!session['user']) {
            redirect controller: "login", action: "index"
        }
        true
    }

    boolean after() {
        log.info "param: ${params}"
        return true
    }

//    void afterView() {
//        // no-op
//    }
}
