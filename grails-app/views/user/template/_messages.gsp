<g:each in="${inboxList}" var="post">
    <div class="well" id="readItemInbox${post.id}">
        <div class="row">
            <div class="col-sm-3">
                <a href='${createLink(controller: 'user', action: 'profile', params: [userId: post.createdBy.id])}'>
                    <ls:userImage userId="${post.createdBy.id}"/>
                </a>
            </div>

            <div class="col-sm-9">
                <div class="row">
                    <span class="pull-left">${post.createdBy.fullName}</span>
                    <span class="col-sm-3 text-muted">
                        <a href='${createLink(controller: 'user', action: 'profile', params: [userId: post.createdBy.id])}'>@${post.createdBy.userName}</a>
                    </span>
                    <span class="col-sm-3 pull-right"><a href='${createLink(controller: 'topic', action: 'showTopic',params: [topicId:post.topic.id])}'>${post.topic.name}</a></span>
                </div>

                <div class="row">
                    <p class="description">
                        ${post.description}
                    </p>
                </div>

                <div class="row">
                    <div class="col-xs-4">
                        <span class="col-sm-1 pull-left"><i class="fa fa-facebook"></i></span>
                        <span class="col-sm-1 pull-left"><i class="fa fa-twitter"></i></span>
                        <span class="col-sm-1 pull-left"><i class="fa fa-google-plus"></i></span>
                    </div>

                    <div class="col-xs-8">
                        <span style="margin-right:10px;">
                            <ls:checkResourceType resourceId="${post.id}"/>
                        </span>
                        <span  style="margin-right:10px;">
                            <ls:isRead resource="${post}"/>
                        </span>
                        <span  style="margin-right:10px;">
                            <a href='${createLink(controller: 'resource', action: 'show',params: [resourceId: post.id])}' class='custom-inline'>
                                View Post
                            </a>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</g:each>
