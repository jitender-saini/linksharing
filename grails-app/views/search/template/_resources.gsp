<div class="container-fluid col-md-12 pull-right">
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="panel-title row">
                <div class="col-md-12">Posts</div>
            </div>
        </div>

        <div class="panel-body">
            <g:each in="${posts}" var="post">
                <div class="well">

                    <div class="row">
                        <div class="col-sm-3">
                            <ls:userImage userId="${post.createdBy.id}"/>
                        </div>
                        <div class="col-sm-9">
                            <div class="row">
                                <span class="col-sm-6"><a href="${g.createLink(controller: 'user',action: 'profile',params: ['resourceSearchCO.id':post.createdBy.id])}">${post.createdBy.fullName}</a></span>
                                <span class="col-sm-6 text-muted">@${post.createdBy.userName}</span>
                            </div>
                            <div class="row">
                                <h4>${post.topic.name}</h4>
                                <p>
                                    ${post.description}
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <span class="col-sm-1 pull-left">
                            <i class="fa fa-facebook"></i>
                        </span>
                        <span class="col-sm-1 pull-left">
                            <i class="fa fa-twitter"></i> </span>
                        <span class="col-sm-1 pull-left">
                            <i class="fa fa-google-plus"></i></span>
                        <span class="pull-right" style="margin-right:10px;text-decoration:underline"><a href="${g.createLink(controller: 'resource',action: 'show',resourceId: post.id)}">view post</a></span>
                        <span class="pull-right" style="margin-right:10px;text-decoration:underline"><ls:isRead resource="${post}"/> </span>
                        <span class="pull-right" style="margin-right:10px;text-decoration:underline"><ls:checkResourceType resource="${post.id}"/> </span>
                    </div>
                </div>
            </g:each>
        </div>
    </div>
</div>