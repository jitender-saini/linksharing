<html>
<head>
    <meta name="layout" content="main"/>
    <title>Resource</title>
</head>

<body>
<div class="col-xs-6">
    <div class="panel panel-default">
        <div class="panel-heading custom-heading">
            <span class="panel-title">Resource</span>
        </div>

        <div class="panel-body custom-body" id="ratings">

            <g:render template="/resource/template/show" collection="${resource}" var="resource"/>
        </div>
    </div>
</div>

<div class="col-xs-6">
    <ls:showTrendingTopic/>
</div>

</body>
</html>