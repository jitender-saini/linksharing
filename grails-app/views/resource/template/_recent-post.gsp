<div class="panel panel-default">
    <div class="panel-heading">
        <span class="panel-title">Post</span>
    </div>

    <div class="panel-body">
        <!--FOr User Image,topic ,rating-->
        <div class="row">
            <div class="col-sm-3">
                <ls:userImage userId="${post.createdBy.id}"/>
            </div>

            <div class="col-sm-6">
                <h5>${post.createdBy.firstName} ${post.createdBy.lastName}</h5>
                <h6><a>@${post.createdBy.userName}</a></h6>
            </div>

            <div class="col-sm-3">
                <a href=""><h5><u>${post.topic.name}</u></h5></a>
                <i class="fa fa-star" aria-hidden="true"></i>
                <i class="fa fa-star" aria-hidden="true"></i>
                <i class="fa fa-star" aria-hidden="true"></i>
                <i class="fa fa-star-half-o" aria-hidden="true"></i>
                <i class="fa fa-star-o" aria-hidden="true"></i>

            </div>

        </div>
        <!--For full Post-->
        <div class="row post-text">
            <div class="col-sm-12">
                <p>${post.description}</p>

            </div>
        </div>

        <div class="row">

            <div class="container-fluid col-md-6 pull-left">
                <div class="row">
                    <div class="col-sm-3">
                        <img src="http://vignette1.wikia.nocookie.net/logopedia/images/f/fb/Facebook_icon_2013.svg"
                             height="30" width="30">
                    </div>

                    <div class="col-sm-3">
                        <img src="https://upload.wikimedia.org/wikipedia/commons/f/f3/Twitter_icon.png"
                             height="30px"
                             width="30px">
                    </div>

                    <div class="col-sm-3">
                        <img src="https://upload.wikimedia.org/wikipedia/commons/e/ed/Google_Plus_icon.svg"
                             height="30px" width="30px">
                    </div>
                </div>
            </div>

            <div class="container-fluid col-md-6 pull-right">
                <div class="row">
                    <div class="col-sm-3">
                        <a href="#">
                            <p class="post-option">Download</p>
                        </a>
                    </div>

                    <div class="col-sm-2">
                        <a href="#">
                            <p class="post-option">Edit</p>
                        </a>
                    </div>

                    <div class="col-sm-4">
                        <a href="#">
                            <p class="post-option">View-Site</p>
                        </a>
                    </div>

                    <div class="col-sm-3">
                        <a href="#">
                            <p class="post-option">Delete</p>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>