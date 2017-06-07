<div class="container-fluid col-md-12 pull-right">
    <div class="panel panel-default ">
        <div class="panel-heading custom-heading " >
            <div class="panel-title row">
                <div class="col-md-12 ">Inbox</div>
            </div>
        </div>

        <div class="panel-body custom-body">
            <div id="inboxMessages">
                <g:render template="template/messages" model="[inboxList: inboxList, unReadCount:unReadCount]"/>
            </div>
            <div>
                <util:remotePaginate controller='user' action="inbox" total="${unReadCount}"
                                     update="inboxMessages" max="5" />
            </div>
        </div>
    </div>
</div>
