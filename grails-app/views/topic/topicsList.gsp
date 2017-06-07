<head>
    <meta name="layout" content="main"/>
</head>


<body>
<div class="container-fluid">
    <div class="my-panel">
        <div class="panel panel-default">
            <div class="panel-heading custom-heading">
                <div class="row">

                    <div class="col-sm-6">
                        <span class="panel-title">Users</span>
                    </div>

                    <div class="col-sm-3">
                        <div class="dropdown pull-right">
                            <button class="btn btn-default dropdown-toggle" type="button"
                                    data-toggle="dropdown">Topics
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><g:link controller="topic" action="topicList">All Topics</g:link></li>
                                <li><g:link controller="topic" action="topicList"
                                            params="[visibility: 'PUBLIC']">Public Topics</g:link></li>
                                <li><g:link controller="topic" action="topicList"
                                            params="[visibility: 'PRIVATE']">Private Topics</g:link></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-sm-3">
                        <g:form class="navbar-form" controller="topic" action="topicList">

                            <div class="inner-addon left-addon" style="margin-top: -10px">
                                <i class="fa fa-search"></i>
                                <input type="text" name="q" class="form-control search-box" placeholder="search"/>
                            </div>
                            <!--            <button type="submit" class="btn btn-default">Submit</button>-->
                        </g:form>
                    </div>

                </div>
            </div>

            <div class="panel-body">

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <g:sortableColumn property="id" title="Id"/>
                        <g:sortableColumn property="name" title="Name"/>
                        <g:sortableColumn property="visibility" title="Visibility"/>
                        <g:sortableColumn property="createdBy" title="CreatedBy"/>
                        <g:sortableColumn property="dateCreated" title="DateCreated"/>
                        <g:sortableColumn property="lastUpdated" title="lastUpdated"/>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each var="topic" in="${topics}">
                        %{--<g:if test="${topic.visibility == 'PUBLIC'}">--}%
                            <tr style="background-color:#3ece45">
                        %{--</g:if>--}%
                        %{--<g:else>--}%
                            %{--<tr style="background-color: #FF3C3C">--}%
                        %{--</g:else>--}%
                        <td>${topic.id}</td>
                        <td>
                            <a href='${createLink(controller: 'topic', action: 'topicList', params: [topicId: topic.id])}'
                               style="color: black">
                                ${topic.name}
                            </a>
                        </td>
                        <td>${topic.visibility}</td>
                        <td>${topic.createdBy.fullName}</td>
                        <td>${topic.dateCreated}</td>
                        <td>${topic.lastUpdated}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <g:paginate max="5" total="${count}"/>
            </div>

        </div>

    </div>
</div>
</body>
</html>

