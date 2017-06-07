<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="container-fluid">
    <div class="col-xs-7">
        <div class="panel panel-default">
            <div class="panel-heading custom-heading">
                <span class="panel-title">Recent Posts</span>
            </div>

            <div class="panel-body custom-body">
                <g:render template="/resource/template/post" collection="${recentPost}" var="post"/>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading custom-heading">
                <span class="panel-title">Top Posts</span>
                %{--<span class="pull-right">--}%
                    %{--<select name="topic" class="form-control pull-right" id="topic">--}%
                        %{--<option value="">Today</option>--}%
                        %{--<option value="2">1 Week</option>--}%
                        %{--<option value="12">1 Month</option>--}%
                        %{--<option value="9">1 year</option>--}%
                    %{--</select>--}%
                %{--</span>--}%
            </div>

            <div class="panel-body custom-body">
                <g:render template="/resource/template/post" collection="${topPost}" var="post"/>

            </div>
        </div>
    </div>

    <div class="col-xs-5">
        <g:render template="/login/template/login"/>
        <g:render template="template/forgetPassword"/>
        <g:render template="/user/template/register"/>
    </div>

</div>
</body>
</html>