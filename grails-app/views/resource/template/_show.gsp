<div class="well">
    <div class="row">
        <div class="col-xs-3">
            <ls:userImage userId="${resource.createdBy.id}"/>
        </div>

        <div class="col-xs-5">
            <h5>${resource.createdBy.fullName}</h5>
            <h4>@${resource.createdBy.userName}</h4>
        </div>

        <div class="col-xs-4">
            <div class="icon-bar" id="rating">
            <ls:resourceRating resourceId="${resource.id}"/>
            </div>
            <ls:isSubscribed resourceId="${resource.id}">

                <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle " type="button"
                            data-toggle="dropdown">Rating
                    </button>
                    <ul class="dropdown-menu">
                        <li><a  onclick="insertRating(${resource.id}, 1)"><span class='fa fa-star'></span></a></li>
                        <li><a  onclick="insertRating(${resource.id}, 2)"><span class='fa fa-star'></span><span class='fa fa-star'></span></a></li>
                        <li><a  onclick="insertRating(${resource.id}, 3)"><span class='fa fa-star'></span><span class='fa fa-star'></span><span class='fa fa-star'></span></a></li>
                        <li><a  onclick="insertRating(${resource.id}, 4)"><span class='fa fa-star'></span><span class='fa fa-star'></span><span class='fa fa-star'></span><span class='fa fa-star'></span></a></li>
                        <li><a  onclick="insertRating(${resource.id}, 5)"><span class='fa fa-star'></span><span class='fa fa-star'></span><span class='fa fa-star'></span><span class='fa fa-star'></span><span class='fa fa-star'></span></a></li>
                    </ul>
                </div>
            </ls:isSubscribed>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row row-sm-12">
            <p>${resource.description}</p>
        </div>
    </div>

    <div class="row">
        <div class="container-fluid col-xs-3 pull-left">
            <div class="row">
                <div class="col-xs-4">
                    <i class="fa fa-facebook fa-2x"></i>
                </div>

                <div class="col-xs-4">
                    <i class="fa fa-twitter fa-2x"></i>
                </div>

                <div class="col-xs-4">
                    <i class="fa fa-google-plus fa-2x"></i>
                </div>
            </div>
        </div>
        <ls:isLoggedIn>

            <div class="container-fluid col-md-9 pull-right text-right">
                <div class="row">
                    <div class="col-xs-4">
                        <ls:checkResourceType resourceId="${resource.id}"/>
                    </div>

                    <div class="col-xs-4">
                        <ls:isRead resource="${resource}"/>
                    </div>

                    <div class="col-xs-4">
                        <div class="container-fluid">
                            <ls:isAdminOrCreatorOfResource resourceId="${resource.id}">
                                <a href="javascript:void(0)" class="editResource${resource.id} col-sm-3"
                                   onclick="editResource(${resource.id})">
                                    <span class="fa fa-edit fa-2x"></span>
                                </a>
                                <a href="javascript:void(0)" class="deleteResource${resource.id} col-sm-3"
                                   onclick="deleteResource(${resource.id})">
                                    <span class="fa fa-trash fa-2x"></span>
                                </a>
                            </ls:isAdminOrCreatorOfResource>
                        </div>
                    </div>
                </div>
            </div>

        </ls:isLoggedIn>
    </div>

    <div class="container-fluid">
        <div class="row form-group">
            <div hidden id="description${resource.id}" class=" row row-sm-12 container-fluid">
                <g:form controller="resource" action="editResource" method="post">
                    <input type="hidden" name="resourceId" value="${resource.id}"/>
                    <label for="desc">Description:</label>
                    <textarea name="description" id="desc" rows="4" style="min-width: 100%"
                              class="form-control form-group">${resource.description}</textarea>
                    <button type="submit" class="btn btn-success btn-block form-control">Save</button>
                </g:form>
            </div>
        </div>
    </div>
</div>
