<div class="panel panel-default">
    <div class="panel-heading custom-heading">
        <span class="panel-title">Trending Topics</span>
    </div>

    <div class="panel-body custom-body">
        <ul class="list-group">
            <g:each in="${trendingTopics}" var="trendingTopic">
                <li class="list-group-item">
                    <div class="row" style="margin-bottom:5px">
                        <div class="col-sm-3">
                            <ls:userImage userId="${trendingTopic.createdBy.id}"/>
                        </div>

                        <div class="col-sm-9">
                            <div class="row">
                                <div class="row">
                                    %{--<ls:showEditTopic topicId="${trendingTopic.id}"/>--}%
                                </div>

                                <div class="row">
                                    <span class="col-sm-12 pull-left">
                                        <a href='${createLink(controller: 'topic', action: 'showTopic',params: [topicId:trendingTopic.id])}'>${trendingTopic.name}</a>
                                    </span>
                                </div>
                            </div>

                            <div class="row">
                                <span class="col-sm-4 text-muted">@${trendingTopic.createdBy.userName}</span>
                                <span class="col-sm-4 text-muted">Subscriptions</span>
                                <span class="col-sm-4 text-muted pull-right">Posts</span>
                            </div>

                            <div class="row">
                                <span class="col-sm-4"><ls:showSubscribe topicId="${trendingTopic.id}"/></span>
                                <span class="col-sm-4"><ls:getSubscriptionCount topicId="${trendingTopic.id}"/></span>
                                <span class="col-sm-4"><ls:getResourceCount topicId="${trendingTopic.id}"/></span>
                            </div>
                        </div>
                    </div>
                    %{--<ls:canUpdateTopic topicId="${trendingTopic.id}"/>--}%
                </li>
            </g:each>
        </ul>
    </div>
</div>