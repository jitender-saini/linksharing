<html>
<head>
    <meta name="layout" content="main"/>
    <title>Show topic</title>
</head>

<body>
<div class="col-xs-5">
    <ls:showUserDetails userId="${user.id}"/>
    <g:render template="template/createdTopic" model="[createdTopics: createdTopics]"/>
    <g:render template="/subscription/template/subscriptions"/>
</div>

<div class="col-sm-7">
    <div class="panel panel-default">
        <div class="panel-heading custom-heading">
            <span class="panel-title">My Posts</span>
            %{--<span class="pull-right">--}%
                %{--<select name="topic" class="form-control pull-right" id="topic">--}%
                    %{--<option value="">Today</option>--}%
                    %{--<option value="2">1 Week</option>--}%
                    %{--<option value="12">1 Month</option>--}%
                    %{--<option value="9">1 year</option>--}%
                %{--</select>--}%
            %{--</span>--}%
        </div>

        <div class="panel-body custom-body scrollable scrollable-lg">
            <g:render template="/resource/template/post" collection="${createdPost}" var="post"/>

        </div>
    </div>
</div>
</body>
</html>

