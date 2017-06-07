<div class="form-group editTopic" style="padding-bottom:25px" data-id="editTopic${topic.id}">
    <div class="col-xs-5">
        <input type="text" placeholder="Grails" class="form-control" value="${topic.name}">
    </div>

    <div class="col-xs-2">
        <button type="button" class="btn btn-success buttonSave" id="topicSaveButton${topic.id}"
                data-url="${g.createLink(controller: 'topic',action: 'updateTopicName')}">Save</button>
    </div>

    <div class="col-xs-2">
        <button type="button" class="btn btn-danger buttonCancel" data-id="${topic.id}"
                id="topicCancelButton${topic.id}">Cancel</button>
    </div>
</div>