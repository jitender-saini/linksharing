package com.ttn.linksharing

class UserInterceptor {
    UserInterceptor() {
        match(controller: 'user', action: 'usersList')
        match(controller: 'user', action: 'toggleActive')
    }

    boolean before() {
        User user = session.user
        if (user) {
            if (!user.isAdmin) {
                redirect(controller: 'user')
            }
        }
        true
    }

    boolean after() { true }

//    void afterView() {
//        // no-op
//    }
}
