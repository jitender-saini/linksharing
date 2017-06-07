/**
 * Created by jitender on 9/5/17.
 */

var nameIsErroneous = true;
var passwordIsErroneous = true;
var passwordMatchIsErroneous = true;
var userNameIsErroneous = true;
var emailIsErroneous = true;

/*when page loads all the fields are loaded with blank values ... removes any chances of GUI error*/
$(document).ready(function(){
    $("#firstName").val("");
    $("#lastName").val("");
    $("#email").val("");
    $("#userName").val("");
});

/*Single function to be called to show error message*/
function showError(id, message, errorId){
    if($("#"+id).parent().find("#"+errorId).length >0 &&
        $("#"+id).parent().find("#"+errorId).text().trim() == message
    ) return;
    if($("#"+id).parent().find("#"+errorId).text().trim() != message) hideError(errorId);
    var errorHtml = '<div class="errorRegistration" id="'+errorId+'">'+message+'</div>';
    $("#"+id).parent().append(errorHtml).hide(0);
    $("#"+errorId).hide(0);
    $("#"+id).parent().show(0);
    $("#"+errorId).show(600);
}

/*Single function to be called to hide error message*/
function hideError(errorId){
    $("#"+errorId).hide(500,function(){
        $("#"+errorId).remove()});
}

/*Validation to to check that user enters only english alphabets including space*/
function alphabetsOnly(eleId){
    var  errorMessage="Enter english literals for Name.", errorId = "alphabetError";
    var text = $("#"+eleId).val();
    nameIsErroneous = false;
    if (text.trim()!="")
    {
        var ch, i;
        for(i=0; i<text.length; i++)
        {
            ch = text.charCodeAt(i);
            if((ch <65 || (ch>90 && ch<97) || ch>122)&& ch!=32)
            {
                ch = text.charAt(i);
                showError(eleId,errorMessage,errorId);
                nameIsErroneous = true;
                break;
            }
        }
        if(nameIsErroneous ==false)
            hideError(errorId);
    }
    else
    {
        nameIsErroneous = true;
        errorMessage = "User name can't be empty";
        showError(eleId,errorMessage,errorId);
    }
}
/*Validate the password*/
function checkPassword(eleId,passLength){
    var  errorMessage="Password length should be at least "+passLength, errorId = "passwordError";
    var pass = $("#"+eleId).val();
    passwordIsErroneous = false;
    if(pass.length != pass.trim().length) {
        passwordIsErroneous=true;
        $("#password-confirm").attr("disabled","disabled");
        errorMessage = "Password can't start or end with space";
        showError(eleId,errorMessage,errorId);
    }
    else if(pass.length<passLength) {
        passwordIsErroneous=true;
        $("#password-confirm").attr("disabled","disabled");
        showError(eleId,errorMessage,errorId);
    }
    else {
        passwordIsErroneous =false;
        $("#password-confirm").removeAttr("disabled");
        hideError(errorId);
    }
}

/*    validate userName */
function checkUserName(eleId,uNameLength) {
    console.log(uNameLength)
    var  errorMessage="UserName length should be at least "+uNameLength, errorId = "userNameError";
    var uName = $("#"+eleId).val();
    userNameIsErroneous = false;
    if(uName.length != uName.trim().length){
        userNameIsErroneous = true;
        errorMessage = "UserName can't start or end with space";
        showError(eleId,errorMessage,errorId);
    }
    else if (uName.length<uNameLength){
        userNameIsErroneous = true;
        showError(eleId,errorMessage,errorId);
    }
    else {
        userNameIsErroneous = false;
        hideError(errorId);
    }
}

/*Matches both the passwords*/
function confirmPassword(eleId,compareWith){
    var  errorMessage="Password doesn't match", errorId = "confirmPasswordError";
    passwordMatchIsErroneous = false;
    if($("#"+eleId).val() != $("#"+compareWith).val()) {
        passwordMatchIsErroneous=true;
        showError(eleId,errorMessage,errorId);
    }
    else {
        hideError(errorId);
        passwordMatchIsErroneous = false;
    }
}
/*Validate proper email address*/
function checkEMail(eleId){
    emailIsErroneous = false;
    var  errorMessage="Enter correct email address", errorId = "emailRegExError";
    var email = $("#"+eleId).val();
    var emailRegEx = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|in|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;
    if(!emailRegEx.test(email)) {
        emailIsErroneous = true;
        showError(eleId,errorMessage,errorId);
    }
    else {
        emailIsErroneous = false;
        hideError(errorId);
    }
}

/*Trigger functions to validate on different events*/
$("#firstName").on("focusout",function(){
    var id = ($(this).attr('id'));
    alphabetsOnly(id);
});

$("#lastName").on("focusout",function(){
    var id = ($(this).attr('id'));
    alphabetsOnly(id);
});

$("#email").on("focusout",function(){
    var id = ($(this).attr('id'));
    checkEMail(id);
})

$("#userName").on("focusout keyup",function () {
    var id = ($(this).attr('id'));
    var userNameLength= 5;
    checkUserName(id,userNameLength);

})

$("#password").on("focusout keyup",function(){
    var passLength = 6;
    var id = ($(this).attr('id'));
    checkPassword(id,passLength);
})

$("#password-confirm").on("focusout",function(event){
    event.stopPropagation();
    var compareWith = "password";
    var id = ($(this).attr('id'));
    confirmPassword(id,compareWith);
})

/*On submit button click it re-checks all the fields to validate form*/
$("#registerForm").submit(function(e){
    e.preventDefault();
    if(!nameIsErroneous && !passwordIsErroneous && !passwordMatchIsErroneous && !userNameIsErroneous && !emailIsErroneous) {
        $(this).unbind("submit").submit();
    }
    if(nameIsErroneous) {
        $("#firstName").trigger("focusout");
    }
    if(nameIsErroneous) {
        $("#lastName").trigger("focusout");
    }
    if(passwordIsErroneous) {
        $("#password").trigger("focusout");
    }
    if(passwordMatchIsErroneous) {
        $("#password-confirm").trigger("focusout");
    }
    if(emailIsErroneous) {
        $("#email").trigger("focusout");
    }
    if(userNameIsErroneous) {
        $("#userName").trigger("focusout");
    }
})
