<div class="panel panel-default">
    <div class="panel-heading custom-heading">
        <div class="panel-title row">
            <div class="col-xs-4">My Topics</div>

            %{--<div class="col-md-8">--}%
                %{--<g:form role="search" controller="search" action="show">--}%
                    %{--<div class="input-group">--}%
                        %{--<g:textField width="20px" name="q" type="text" class="form-control" placeholder="Search"/>--}%
                        %{--<div class="input-group-addon">--}%
                            %{--<a onclick="$(parent).parent().submit();--}%
                            %{--return false;" style="cursor: pointer"><i class="fa fa-search"></i>--}%
                            %{--</a>--}%
                        %{--</div>--}%
                    %{--</div>--}%
                %{--</g:form>--}%
            %{--</div>--}%
        </div>
    </div>
<div class="panel-body custom-body scrollable">
    <ul class="list-group">
        <g:each in="${createdTopics}" var="topicVO">
            <li class="list-group-item">
                <div class="row" style="margin-bottom:5px">
                    <div class="col-sm-3">
                        <ls:userImage userId="${topicVO.createdBy.id}"/>
                    </div>

                    <div class="col-sm-9">
                        <div class="row">
                        </div>

                        <div class="row">
                            <span class="col-sm-12 pull-left">
                                <a data-id="topic-${topicVO.id}"
                                   href="${g.createLink(controller: 'topic', action: 'showTopic', params: [topicId: topicVO.id])}">${topicVO.name}</a>
                            </span>
                        </div>

                        <div class="row">
                            <div class="col-sm-4 text-muted"><span>@${topicVO.createdBy.userName}</span></div>

                            <div class="col-sm-4 text-muted"><span>Subscriptions</span></div>

                            <div class="col-sm-4 text-muted pull-right"><span>Posts</span></div>
                        </div>

                        <div class="row">
                            <ls:notCreatorOfTopic topicId="${topicVO.id}">
                                <span class="col-sm-4"><ls:showSubscribe topicId="${topicVO.id}"/></span>
                            </ls:notCreatorOfTopic>
                            <span class="col-sm-4"><ls:getSubscriptionCount topicId="${topicVO.id}"/></span>
                            <span class="col-sm-4"><ls:getResourceCount topicId="${topicVO.id}"/></span>
                        </div>
                    </div>
                </div>
            </li>
        </g:each>
    </ul>
</div>
</div>