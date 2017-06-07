<div class="well custom-body">
    <div class="row">
        <div class="col-sm-4">
            <a href='${createLink(controller: 'user', action: 'profile', params: [userId: user.id])}'>
                <ls:userProfileImage userId="${user.id}"/>
            </a>
        </div>

        <div class="col-sm-8">
            <div class="row">
                <h4><a href='${createLink(controller: 'user', action: 'profile', params: [userId: user.id])}'>${user.fullName}</a>
                </h4>

                <div class="text-muted">@${user.userName}</div>
            </div>

            <div class="col-xs-6">
                <h5>Subscriptions</h5>
                <a href="#"><ls:getSubscriptionCount user='${user}'/></a>
            </div>

            <div class="col-xs-6">
                <h5>Topics</h5>
                <a href="#"><ls:topicCount user="${user}"/></a>

            </div>
        </div>
    </div>
</div>