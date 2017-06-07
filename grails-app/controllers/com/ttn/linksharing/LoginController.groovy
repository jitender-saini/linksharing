package com.ttn.linksharing

class LoginController {
    def assetResourceLocator

    def index() {
        User user = session.user
        if (user) {
            redirect(controller: "user", action: "index")
        } else {
            render view: 'index', model: [topPost   : Topic.getTopPost(),
                                          recentPost: Resource.getRecentPost()]
        }
    }

    def loginHandler(String userName, String password) {
        User user = User.createCriteria().get {
            projections {
                or {
                    eq('userName', userName)
                    eq('email', userName)
                }
                eq('password', password)
            }
        }
        if (user) {
            if (user.isActive) {
                session.user = user
                redirect(controller: "user", action: "index")
            } else {
                flash.error = "Your account is not active"
                redirect(controller: "login", action: "index")
            }
        } else {
            flash.error = "User not found"
            redirect(controller: "login", action: "index")
        }
    }

    def logout() {
        session.invalidate()
        forward(controller: "login", action: "index")
    }


    def image(Long id) {
        User user = User.get(id)
        byte[] photo

        if (user.profilePic) {
            photo = user.profilePic
        } else {
            photo = assetResourceLocator.findAssetForURI('user.png').byteArray
        }
        response.contentType = 'image/png'
        OutputStream out = response.getOutputStream()
        out.write(photo)
        out.flush()
        out.close()
    }
}
