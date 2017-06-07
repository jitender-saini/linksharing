<div class="panel panel-default">
    <div class="panel-heading custom-heading">
        <div class="row-fluid user-row">
            Register
        </div>
    </div>

    <div class="panel-body regForm">
        <g:form controller="user" action="register"
                enctype="multipart/form-data">
            <div class="form-element-container"><input id="firstName" name="firstName"
                                                       placeholder="First Name" required
                                                       type="text" class="input-txt form-control"/></div>

            <div class="form-element-container"><input id="lastName" name="lastName"
                                                       placeholder="Last Name" required
                                                       type="text" class="input-txt form-control"/></div>

            <div class="form-element-container"><input id="email" name="email" placeholder="Email Address" required
                                                       type="email" class="input-txt form-control"/></div>

            <div class="form-element-container"><input id="userName" name="userName"
                                                       placeholder="User Name" required
                                                       type="text" class="input-txt form-control"/></div>

            <div class="form-element-container"><input id="password" name="password"
                                                       placeholder="Password"
                                                       type="password" required pattern=".{5,20}"
                                                       class="input-txt form-control"/></div>

            <div class="form-element-container"><input id="confirmPassword" name="confirmPassword" required
                                                       placeholder="Re-enter password" type="password" pattern=".{5,20}"
                                                       class="input-txt form-control"/></div>
            <label for="profilePic"><small>Upload Profile Picture</small></label>

            <div class="form-element-container"><input type="file" name="profilePic" id="profilePic"
                                                       accept="image/*"/>
            </div>
            <button type="submit" class="btn btn--right btn-lg btn-success btn-block"
                    id="register">Create My Account</button>
        </g:form>
    </div>
</div>
