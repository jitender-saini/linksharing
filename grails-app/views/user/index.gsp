<html>
<head>
    <meta name="layout" content="main"/>
    <title>Show topic</title>
</head>

<body>
<div class="col-xs-5">
    <div class="row">
        <ls:showUserDetails userId="${session.user.id}"/>
    </div>

    <div class="row">
        <g:render template="/subscription/template/subscriptions"/>
    </div>

    <div class="row">
        <ls:showTrendingTopic/>
    </div>
</div>

<div class="col-xs-7">
    <g:render template="template/inbox"/>
</div>
</body>
</html>