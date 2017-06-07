<div class="panel panel-default">
    <div class="panel-heading custom-heading">
        <div class="row-fluid user-row">
            Edit Profile
        </div>
    </div>

    <div class="panel-body regForm">
        <g:form controller="user" action="updateProfile" id="updateForm"
                enctype="multipart/form-data">
            <div class="row">
                <div class="col-xs-3 text-right ">
                    <label for="fname" class=" form-group">First Name:</label>
                </div>

                <div class="col-xs-9">
                    <input name="firstName" required id="fname"
                           type="text" class="form-inline  form-control" value="${user.firstName}"/>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-3 text-right ">
                    <label for="lname" class=" form-group">Last Name:</label>
                </div>

                <div class="col-xs-9">
                    <input name="lastName" required id="lname" type="text"
                           class="form-control" value="${user.lastName}"/>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-3 text-right ">
                    <label for="uname" class=" form-group">User Name:</label>
                </div>

                <div class="col-xs-9">
                <input name="userName" placeholder="User Name" id="uname" title="UserName length should be between 5-15"
                       type="text" class="input-txt form-control" pattern=".{4,15}" value="${user.userName}"/>
                       </div>
            </div>

            <label for="profilePic"><small>Upload Profile Picture</small></label>

            <div class="form-element-container"><input type="file" name="profilePic" id="profilePic" value=""
                                                       accept="image/*"/>
            </div>
            <button type="submit" class="btn btn-right btn-lg btn-success btn-block"
                    id="update">Update Account</button>
        </g:form><br/>
    </div>
</div>
