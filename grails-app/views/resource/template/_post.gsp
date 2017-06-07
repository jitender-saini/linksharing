<div class="well">
    <div class="row">
        <div class="col-sm-3">
            <a href='${createLink(controller: 'user', action: 'profile', params: [userId: post.createdBy.id])}'>
                <ls:userImage userId="${post.createdBy.id}"/>
            </a>
        </div>

        <div class="col-xs-9">
            <div class="row">
                <span class="pull-left"><a
                        href='${createLink(controller: 'user', action: 'profile', params: [userId: post.createdBy.id])}'>${post.createdBy.fullName}</a>
                </span>

                <div class="col-xs-3 text-muted">@${post.createdBy.userName}</div>

                <div class="col-xs-6 pull-right text-right"><a
                        href='${createLink(controller: 'topic', action: 'showTopic', params: [topicId: post.topic.id])}'>${post.topic.name}</a>
                </div>
            </div>

            <div class="row">
                <p class="description">
                    ${post.description}
                </p>
            </div>

            <div class="row">
                <span class="col-sm-1 pull-left"><i class="fa fa-facebook"></i></span>
                <span class="col-sm-1 pull-left"><i class="fa fa-twitter"></i></span>
                <span class="col-sm-1 pull-left"><i class="fa fa-google-plus"></i></span>
                <span class="pull-right" style="margin-right:10px;text-decoration:underline">
                    <ls:isLoggedIn>
                        <span style="margin-right:10px;">
                            <ls:checkResourceType resourceId="${post.id}"/>
                        </span>
                        <span style="margin-right:10px;" id= 'ReadingItems'>
                            <ls:isRead resource="${post}"/>
                        </span>
                    </ls:isLoggedIn>

                    <a href='${createLink(controller: 'resource', action: 'show', params: [resourceId: post.id])}'>View Post</a>
                </span>
            </div>
        </div>
    </div>
</div>
