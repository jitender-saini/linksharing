<div class="panel panel-default ">
    <div class="panel-heading custom-heading">
        <div class="row-fluid user-row">
            Login
        </div>
    </div>

    <div class="panel-body">
        <g:form id="loginForm" controller="login" action="loginHandler" method="GET" class="form-signin">
            <fieldset>
                <label class="panel-login">
                    <div class="login_result"></div>
                </label>
                <input class="form-control input-txt" placeholder="User Name/Email Id" id="userName" type="text"
                       name="userName" required="required">
                <input class="form-control input-txt" placeholder="Password" id="password" type="password"
                       name="password" required="required">
                <br>
                <button type="submit" class="btn btn--right btn-lg btn-success btn-block"
                        id="login">Sign in</button>
            </fieldset>
        </g:form><br/>
        <p>Forget Password <a href="javascript:void(0)" data-toggle="modal" data-target="#forgotPassword">Click Here</a></p>
    </div>
</div>