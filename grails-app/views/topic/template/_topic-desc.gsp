<div class="well">
    <div class="row">
        <div class="col-xs-4">
            <a href='${createLink(controller: 'user', action: 'profile', params: [userId: topic.createdBy.id])}'>
                <ls:userProfileImage userId="${topic.createdBy.id}"/>
            </a>
        </div>

        <div class="col-sm-8">
            <div class="row">
                <p><h4>${topic.name}</h4>( ${topic.visibility} )</p>
            </div>

            <div class="row">
                <div class="col-sm-4">
                    <h5>@${topic.createdBy.userName}</h5>
                    <ls:notCreatorOfTopic topicId="${topic.id}">
                        <div id="subscription">
                            <ls:showSubscribe topicId="${topic.id}"/>
                        </div>
                    </ls:notCreatorOfTopic>
                </div>

                <div class="col-sm-4">
                    <h5>Subscriptions</h5>
                    <h5><ls:getSubscriptionCount topicId="${topic.id}"/></h5>
                </div>

                <div class="col-sm-4">
                    <h5>Posts</h5>
                    <h5><ls:getResourceCount topicId="${topic.id}"/></h5>
                </div>
            </div>


            <div class="row form-group">
                <ls:isSubscribedToTopic topicId="${topic.id}">
                    <div class="col-xs-6">
                        <div class="dropdown pull-right" id="changeSeriousness">
                            <button class="btn btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown">Seriousness
                                <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li><a href="#"
                                       onclick="updateSeriousness(${topic.id}, 'serious')">Serious</a>
                                </li>
                                <li><a href="#"
                                       onclick="updateSeriousness(${topic.id}, 'casual')">Not-So-Serious</a>
                                </li>
                                <li><a href="#"
                                       onclick="updateSeriousness(${topic.id}, 'very serious')">Extremely-Serious</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </ls:isSubscribedToTopic>
                %{--<div class="col-sm-3">--}%
                    %{--<a href=""><span class="fa fa-envelope"></span></a>--}%
                %{--</div>--}%

                <ls:isAdminOrCreatorOfTopic topicId="${topic.id}">
                    <div class="col-xs-3">
                        <a href="javascript:void(0)" class="topicEdit${topic.id}"
                           onclick="editTopic(${topic.id})">
                            <span class="fa fa-edit fa-2x"></span>
                        </a>
                    </div>

                    <div class="col-xs-3">
                        <a href="javascript:void(0)" class="topicDelete${topic.id}"
                           onclick="deleteTopic(${topic.id})">
                            <span class="fa fa-trash fa-2x"></span>
                        </a>
                    </div>
                </ls:isAdminOrCreatorOfTopic>
            </div>

            <div class="row">
                <div hidden class="topicId${topic.id} container-fluid">
                    <g:form controller="topic" action="edit" class="form-inline">
                        <input type="hidden" name="topicId" value="${topic.id}"/>

                        <div class="form-group">
                            <label for="title">Title:</label>
                            <input type="text" name="topicName" id="title" value="${topic.name}" required
                                   pattern=".{3,20}" class="form-control" title="This is an error message"/>
                            <button type="submit" class="btn btn-success btn-block" style="margin: auto">Save</button>
                        </div>
                    </g:form>
                </div>
            </div>
        </div>

    </div>
</div>