<g:each in="${subscribedTopic}" var="topic">
    <li class="list-group-item">
        <div class="row" style="margin-bottom:5px">
            <div class="col-sm-3">
                <ls:userImage userId="${topic.createdBy.id}"/>
            </div>

            <div class="col-sm-9">
                <div class="row">
                </div>

                <div class="row">
                    <span class="col-sm-12 pull-left">
                        <a href='${createLink(controller: 'topic', action: 'showTopic', params: [topicId: topic.id])}'>${topic.name}</a>
                    </span>
                </div>

                <div class="row">
                    <div class="col-sm-4 text-muted"><span>@${topic.createdBy.userName}</span></div>

                    <div class="col-sm-4 text-muted"><span>Subscriptions</span></div>

                    <div class="col-sm-4 text-muted pull-right"><span>Posts</span></div>
                </div>

                <div class="row">
                    <ls:notCreatorOfTopic topicId="${topic.id}">
                        <span class="col-sm-4"><ls:showSubscribe topicId="${topic.id}"/></span>
                    </ls:notCreatorOfTopic>
                    <span class="col-sm-4"><ls:getSubscriptionCount topicId="${topic.id}"/></span>
                    <span class="col-sm-4"><ls:getResourceCount topicId="${topic.id}"/></span>
                </div>
            </div>
        </div>
    </li>
</g:each>
