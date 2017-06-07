<div class="panel panel-default">
    <div class="panel-heading custom-heading">
        <span class="panel-title">Subscriptions</span>
    </div>

    <div class="panel-body custom-body">
        <ul class="list-group">
            <div id="subscriptions">
                <g:render template="/subscription/template/show" model="[subscribedTopic: subscribedTopic]"/>
            </div>
        </ul>

        <div>
            <util:remotePaginate controller='user' action="subscriptions" total="${subscriptionCount}"
            update="subscriptions" max="5"/>
        </div>
    </div>
</div>