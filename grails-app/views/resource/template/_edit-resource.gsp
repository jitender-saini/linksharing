<div class="form-group editResource" style="padding-bottom:25px" data-id="editResource${resource.id}">
    <div class="col-xs-5">
        <textarea placeholder="Resource Description" class="form-control"
                  id="resourceEditBox${resource.id}" value="${resource.description}"></textarea>
    </div>

    <div class="col-xs-2">
        <button type="button" class="btn btn-success" data-id="${resource.id}"
                id="resourceSaveButton${resource.id}" data-url="${g.createLink(controller: 'resource',action: 'editDescription')}">Save</button>
    </div>

    <div class="col-xs-2">
        <button type="button" class="btn btn-danger" data-id="${resource.id}"
                id="resourceCancelButton${resource.id}">Cancel</button>
    </div>
</div>