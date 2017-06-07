<div class="modal fade" id="send-invite" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header custom-heading">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <label class="modal-title">Send Invitation</label>
            </div>

            <div class="modal-body" style="margin-left: 15px;margin-right:15px;">
                <g:form controller="topic" action="invite" class="form-horizontal">
                    <div class="form-group">
                        <div class="control-label col-sm-3"><label for="email" class="pull-left">Email*</label></div>

                        <div class="col-sm-9">
                            <input type="email" id="email" class="form-control pull-right" name="email" required
                                   placeholder="enter the email"/></div>
                    </div>

                    <div class="form-group">
                        <div class="control-label col-sm-3"><label class="pull-left">Topic*</label></div>

                        <div class="col-sm-9">
                            <ls:subscribedTopicList/>
                        </div>
                    </div>  

                    <div class="form-group" style="padding-left:15px;padding-right:15px">
                        <button type="submit" class="btn btn-info ">Invite</button>
                        <button type="button" class="btn btn-danger pull-right" data-dismiss="modal">cancel</button>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>