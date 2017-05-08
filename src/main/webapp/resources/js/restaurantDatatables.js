var ajaxUrl = "/ajax/profile/restaurants/";
var ajaxVoteUrl = "/ajax/profile/votes/";
var datatableApi;
var userVote;
var vote;


$(document).ready(function () {
        userVote = (function () {
            var json = null;
            $.ajax({
                async: false,
                url: "/ajax/profile/user_vote/",
                dataType: "json",
                success: function (data) {
                    json = data;
                }
            });
            return json;
        })();

    getDatatableApi();
});



function getDatatableApi() {
    datatableApi = $('#datatable').DataTable(extendsOpts({
        "ajax": {
            "url": ajaxUrl
        },
        "columns": [
            {
                "data": "id",
                "render": function (data, type, row) {
                    return '<img src="/resources/images/'+data+'.png" alt="image" style="width:90px" class="img-circle"/>';
                }
            },
            {
                "data": "restaurantName",
                "render": function (data, type, row) {
                    return '<span class="table_bold_txt">' + data + '</span>';
                }
            },
            {
                "data": "menu",
                "render": function (data, type, row) {
                    var result = "";
                    for (x in data) {
                        result += data[x].name + "<br>";
                    }
                    return '<span class="table_dishes">' + result + '</span>';
                }
            },
            {
                "data": "menu",
                "render": function (data, type, row) {
                    var result = "";
                    for (x in data) {
                        result += data[x].price + " ₽" + "<br>";
                    }
                    return '<span class="table_dishes">' + result + '</span>';
                }
            },
            {
                "data": "vote.amount",
                "render": function (data, type, row) {
                    if (data == null) {
                        return '<span class="badge">' + 0 + '</span>';
                        ;
                    } else {
                        return '<span class="badge">' + data + '</span>';
                    }
                }
            },
            {
                "render": renderBtn,
                "defaultContent": "",
                "orderable": false
            }
        ],
        "order": [[4, "desc"]]
    }));
}

function renderBtn(data, type, row) {
    if (type == 'display') {
        if (userVote.isVote == false) {
            return '<a class="btn btn-success btn-circle " onclick="addVote(' + row.id + ', ' + ((row.vote == undefined) ? null :
                    '{id:' + row.vote.id + ', amount:' + row.vote.amount + '}' ) + ')"><span class="glyphicon glyphicon-thumbs-up"></span></a>';
        }
        if (userVote.isVote == true && row.vote != undefined && row.vote.id == userVote.voteId) {
            vote = row.vote;
            return '<a class="btn btn-danger btn-circle " onclick="deleteVote(vote)"><span class="glyphicon glyphicon-thumbs-down"></a>';
        }
    }
}

function addVote(restaurantId, vote) {
    confirmNoty('confirm.vote.add', 'updateVote', restaurantId, vote);
}

function updateVote(restaurantId, vote) {
    if (vote == null) {
        saveVote(restaurantId)
    } else {
        var date = new Date().toLocaleDateString().split('.');
        vote.date = date[2] + '-' + date[1] + '-' + date[0];
        incrementVote(vote, restaurantId);
    }
}

// function votes(restaurantId, vote) {
//     if (vote == null) {
//         saveVote(restaurantId)
//     } else {
//         var date = new Date().toLocaleDateString().split('.');
//         vote.date = date[2] + '-' + date[1] + '-' + date[0];
//         incrementVote(vote, restaurantId);
//     }
// }

function deleteVote(vote) {
    var restaurantId = vote.restaurant.id;
    delete vote.restaurant;
    confirmNoty('confirm.vote.delete', 'deleteVote', vote, restaurantId);
}


function saveVote(restaurantId) {
    $.ajax({
        type: "POST",
        url: ajaxVoteUrl + restaurantId,
        dataType: "json",
        success: function (response) {
            vote = response;
            userVote.isVote = true;
            userVote.voteId = vote.id;
            updateTable();
            successNoty('common.voted');
        }
    });
}

function incrementVote(vote, restaurantId) {
    $.ajax({
        type: "POST",
        url: ajaxVoteUrl + "increment/" + vote.id + "&" + restaurantId,
        data: vote,
        dataType: "json",
        success: function (response) {
            vote = response;
            userVote.isVote = true;
            userVote.voteId = vote.id;
            updateTable();
            successNoty('common.voted');
        }
    });
}

function decrementVote(vote, restaurantId) {
    $.ajax({
        type: "POST",
        url: ajaxVoteUrl + "decrement/" + vote.id + "&" + restaurantId,
        data: vote,
        dataType: "json",
        success: function (response) {
            vote = response;
            userVote.isVote = false;
            delete userVote.voteId;
            updateTable();
            successNoty('common.vote_deleted');
        }
    });
}
