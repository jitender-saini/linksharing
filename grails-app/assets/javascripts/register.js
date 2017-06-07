/**
 * Created by jitender on 9/5/17.
 */

jQuery(document).ready(function(){
    $("#userRegistrationForm").validate({
        rules:{
            firstName: "required",
            lastName: "required",
            email:{
                required:true,
                email:true
            },
            password:{
                required:true,
                minlength: 5
            }
        },
        messages:{
            firstName: "Please enter your first name",
            lastName: "Please enter your first name",
            email: {
              required:"Please enter a valid email address",
                email:"Please enter valid email ID"
            },
            password:{
                required: "Please provide a password",
                minlength: "Your password must be at least 5 characters long"
            }
        },
        submitHandler: function(form){
            alert("submit called");
            //form.submit();
            return false;
        }
    });


});
function validateUserRegistrationForm() {
    alert("c   alled");
    jQuery("#userRegistrationForm").valid()
}
