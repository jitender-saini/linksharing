package com.ttn.linksharing

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "500"(view: '/error')
        "404"(view: '/notFound')
        "/test"(controller: "test", action: "noAction")
        "/"(controller: "login", action: "index")
        "/redirectTest"(redirect: '/mytest')
    }
}
