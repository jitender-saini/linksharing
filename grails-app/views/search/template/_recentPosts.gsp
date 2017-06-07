<div class="panel panel-default">
    <div class="panel-heading custom-heading">
        <span class="panel-title">Recent Posts</span>
    </div>

    <div class="panel-body custom-body scrollable scrollable-lg">
        <g:render template="/resource/template/post" collection="${posts}" var="post"/>
        %{--<g:paginate total="" max="5"/>--}%
    </div>
</div>