/**
 * Created by jitender on 16/5/17.
 */

// function makeAjaxcall(url, data, element, callback) {
//     element.parent().prepend($("#ajaxSpinnerImage"));
//     $.ajax({
//         url: url,
//         data: data,
//         success: function (response) {
//             console.log(response);
//             callback(response)
//         }
//     });
// }


if (typeof jQuery !== 'undefined') {
    (function ($) {
        $(document).ajaxStart(function () {
            $('#spinner').fadeIn();
        }).ajaxStop(function () {
            $('#spinner').fadeOut();
        });
    })(jQuery);
}

function updateSeriousness(topicId, seriousness) {
    $.ajax({
        url: "/subscription/update?topicId=" + topicId + "&seriousness=" + seriousness,
        type: 'GET',
        success: function (response) {
            $("#flash").html("<div class='alert alert-success'>" + response.success + "</div>");
            location.reload();
        },
        error: function (response) {
            $("#flash").html("<div class='alert alert-danger'>" + response.error + "</div>");
        }
    })
}


function deleteResource(resourceId) {
    if (confirm("Are you sure you want to delete this resource ?") == true) {
        jQuery.ajax({
            url: "/resource/delete?resourceId=" + resourceId,
            type: 'GET',
            success: function (response) {
                $("#flash").html("<div class='alert alert-success'>" + response.success + "</div>");
            },
            error: function (response) {
                $("#flash").html("<div class='alert alert-danger'>" + response.error + "</div>");
            }
        })
    }
}

function deleteTopic(topicId) {
    if (confirm("Are you sure, you want to delete this topic?") == true) {
        jQuery.ajax({
            url: "/topic/delete?topicId=" + topicId,
            type: 'GET',
            success: function (response) {
                $("#flash").html("<div class='alert alert-success'>" + response.success + "</div>");
            },
            error: function (response) {
                $("#flash").html("<div class='alert alert-danger'>" + response.error + "</div>");
            }
        })
    }
}

function insertRating(resourceId, rating) {
    jQuery.ajax({
        url: "/resource/rating?resourceId=" + resourceId + "&rating=" + rating,
        type: 'GET',
        success: function (result) {
            var builder = "";
            for (var i = 1; i <= 5; i++) {
                if (rating >= i) {
                    builder += "<span class='fa fa-star'></span>";
                } else if (rating > i && rating < i + 1) {
                    builder += "<span class='fa fa-star-half-empty'></span>"

                } else {
                    builder += "<span class='fa fa-star-o'></span>"
                }
            }
            $("#rating").html(builder);
        },
        error: function (response) {
            $("#flash").html("<div class='alert alert-danger'>" + response.error + "</div>");
        }
    })
}

function deleteSubscribe(topicId) {
    jQuery.ajax({
        url: "/subscription/delete?topicId=" + topicId,
        success: function (response) {
            var builder = "<a onClick='subscribeTopic(" + topicId + ")'>Subscribe</a>";
            $("#subscription").html(builder);
            $("#changeSeriousness").hide();
            $("#flash").html("<div class='alert alert-success'>" + response.success + "</div>");
        },
        error: function (response) {
            $("#flash").html("<div class='alert alert-danger'>" + response.error + "</div>");
        }
    })
}
function subscribeTopic(topicId) {
    jQuery.ajax({
        url: "/subscription/save?topicId=" + topicId,
        success: function (response) {
            var builder = "<a onClick='deleteSubscribe(" + topicId + ")'>UnSubscribe</a>";
            $("#subscription").html(builder);
            $("#changeSeriousness").toggle();
            $("#flash").html("<div class='alert alert-success'>" + response.success + "</div>");
        },
        error: function (response) {
            $("#flash").html("<div class='alert alert-danger'>" + response.error + "</div>");
        }
    })
}

function readItem(resourceId) {
    $.ajax({
        url: "/readingItem/readItem?resourceId=" + resourceId,
        success: function (response) {
            var builder = "<a onClick='unReadItem(" + resourceId + ")'>Mark as Unread</a>";
            $("#flash").html("<div class='alert alert-success'>" + response.success + "</div>");
            $("#ReadingItems").html(builder);
            $("#readItemInbox" + resourceId).hide();
        },
        error: function (response) {
            $("#flash").html("<div class='alert alert-danger'>" + response.error + "</div>");
        }
    })
}

function unReadItem(resourceId) {
    $.ajax({
        url: "/readingItem/unReadItem?resourceId=" + resourceId,
        success: function (response) {
            var builder = "<a onClick='readItem(" + resourceId + ")'>Mark as Read</a>"
            $("#flash").html("<div class='alert alert-success'>" + response.success + "</div>");
            $("#ReadingItems").html(builder);
        },
        error: function (response) {
            $("#flash").html("<div class='alert alert-danger'>" + response.error + "</div>");
        }
    })
}


function editResource(resourceId) {
    var id = "#description" + resourceId;
    jQuery(id).show()
}

function editTopic(topicId) {
    var id = ".topicId" + topicId;
    jQuery(id).show()
}

// function globalSearch(q) {
//     console.log(q);
//     $.ajax({
//         url:"search/show?q" +q,
//         type: 'GET',
//         success: function (result) {
//             alert(result)
//         }
//     })
// }


// function myfunc() {
//     var button = document.getElementById("searchButton"),
//         value =  button.form.search.value;
//     button.onclick = function() {
//         globalSearch(value);
//     }
//
// }
