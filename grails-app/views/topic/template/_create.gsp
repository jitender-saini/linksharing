<div class="modal fade" id="topicCreate" role="dialog" data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <label class="modal-title">Create Topic</label>
            </div>

            <div class="modal-body" style="margin-left: 15px;margin-right:15px;">
                <g:form controller="topic" action="create" class="form-horizontal">
                    <div class="form-group">
                        <div class="control-label col-sm-3"><label for="topicName" class="pull-left">Name*</label></div>
                        <div class="col-sm-9">
                            <input type="text" class="form-control pull-right" name="name" id="topicName" required
                                   placeholder="Enter the topic name" pattern=".{3,30}"/></div>
                    </div>

                    <div class="form-group">
                        <div class="control-label col-sm-3"><label for="visibility" class="pull-left">Visibility*</label>
                        </div>

                        <div class="col-sm-9">
                            <select name="visibility" class="form-control pull-right"
                                    id="visibility">
                                <option value="PUBLIC">Public</option>
                                <option value="PRIVATE">Private</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group" style="padding-left:15px;padding-right:15px">
                        <a href="user"><button type="submit" class="btn btn-info ">save</button></a>
                        <button type="button" class="btn btn-danger pull-right" data-dismiss="modal">cancel</button>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>
