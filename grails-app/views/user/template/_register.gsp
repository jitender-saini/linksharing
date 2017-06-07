%{--<head>--}%
    %{--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>--}%
    %{--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--}%
%{--</head>--}%

<div class="panel panel-default">
    <div class="panel-heading custom-heading">
        <div class="row-fluid user-row">
            Register
        </div>
    </div>

    <div class="panel-body regForm">
        <g:form controller="user" action="register" id="userRegistrationForm"
                enctype="multipart/form-data" >
            <div class="form-element-container"><input id="firstName" name="firstName"
                                                       placeholder="First Name"
                                                       type="text" class="input-txt form-control"/></div>

            <div class="form-element-container"><input id="lastName" name="lastName"
                                                       placeholder="Last Name"
                                                       type="text" class="input-txt form-control"/></div>

            <div class="form-element-container"><input id="email" name="email" placeholder="Email Address"
                                                       type="email" class="input-txt form-control"/></div>

            <div class="form-element-container"><input id="userName" name="userName"
                                                       placeholder="User Name"
                                                       type="text" class="input-txt form-control"/></div>

            <div class="form-element-container"><input id="password" name="password"
                                                       placeholder="Password"
                                                       type="password"  pattern=".{5,20}"
                                                       class="input-txt form-control"/></div>

            <div class="form-element-container"><input id="confirmPassword" name="confirmPassword"
                                                       placeholder="Re-enter password" type="password" pattern=".{5,20}"
                                                       class="input-txt form-control"/></div>

            <label for="profilePic"><small>Upload Profile Picture</small></label>

            <div class="form-element-container"><input type="file" name="profilePic" id="profilePic"
                                                       accept="image/*"/>
            </div>
            <button type="button" class="btn btn--right btn-lg btn-success btn-block"
                    id="register" onclick="validateUserRegistrationForm()">Create My Account</button>
        </g:form>
    </div>
</div>

