var ajaxRestaurantUrl = "ajax/profile/restaurants/";
var ajaxVoteUrl = "/ajax/profile/votes/";
var datatableApi;
var userVote;
var vote;

// $(document).ready(function () {
// function clearFilter() {
//     $("#filter")[0].reset();
//     $.get(ajaxUrl, updateTableByData);
// }


$(document).ready(function () {
    getUserVote();
    getDatatableApi();
    // renderDeleteBtn();
});

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxRestaurantUrl,
        success: function () {
            updateTableByData;
            // renderDeleteBtn();
        }
    });
}

function getUserVote() {
    userVote = (function () {
        var json = null;
        $.ajax({
            async: false,
            global: false,
            url: "/ajax/profile/user_vote/",
            dataType: "json",
            success: function (data) {
                json = data;
            }
        });
        return json;
    })();
}

function getDatatableApi() {
    datatableApi = $('#datatable').DataTable(extendsOpts({

        "columns": [
            {
                "data": "restaurantName"
            },
            {
                "data": "menu",
                "render": function (data, type, row) {
                    var result = "";
                    for (x in data) {
                        result += data[x].dishName + "<br>";
                    }
                    return result;
                }

            },
            {
                "data": "menu",
                "render": function (data, type, row) {
                    var result = "";
                    for (x in data) {
                        result += data[x].price + "<br>";
                    }
                    return result;
                }

            },
            {
                "data": "vote.amount",
                "render": function (data, type, row) {
                    if (data == null) {
                        return 0;
                    } else {
                        return data;
                    }

                }
            },
            {
                "render": renderBtn,
                "defaultContent": "",
                "orderable": false

            },
        ],
        "order": [[4, "desc"]],

    }));
}

function renderBtn(data, type, row) {
    if (userVote.isVote == false) {
        return '<a class="btn btn-success" onclick="updateVote(' + row.id + ', ' + ((row.vote == undefined) ? null :
                '{id:' + row.vote.id + ', amount:' + row.vote.amount + '}' ) + ')">Vote</a>';
    }
    if (userVote.isVote == true && row.vote != undefined) {
        if (row.vote.id == userVote.voteId) {
            vote = row.vote;
            return '<a class="btn btn-danger" onclick="deleteVote(vote)">DeleteVote</a>';
        }
    }

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

function saveVote(restaurantId) {
    $.ajax({
        type: "POST",
        url: ajaxVoteUrl + restaurantId,
        async: false,
        global: false,
        dataType: "json",
        success: function (response) {
            vote = response;
            userVote.isVote = true;
            userVote.voteId = vote.id;
            $.get(ajaxRestaurantUrl, updateTableByData);
            // successNoty('common.saved');
            // loadDeleteButton();
        }
    });
}

function deleteVote(vote) {
    var restaurantId = vote.restaurant.id;
    delete vote.restaurant;
    decrementVote(vote, restaurantId);
}

function incrementVote(vote, restaurantId) {
    $.ajax({
        type: "POST",
        url: ajaxVoteUrl + "increment/" + vote.id + "&" + restaurantId,
        data: vote,
        async: false,
        global: false,
        dataType: "json",
        success: function (response) {
            vote = response;
            userVote.isVote = true;
            userVote.voteId = vote.id;
            $.get(ajaxRestaurantUrl, updateTableByData);
            // successNoty('common.saved');
        }
    });
}

function decrementVote(vote, restaurantId) {
    $.ajax({
        type: "POST",
        url: ajaxVoteUrl + "decrement/" + vote.id + "&" + restaurantId,
        // data: '{id:' + vote.id + ', date:' + vote.date + ', amount:' + vote.amount + '}',
        data: vote,
        async: false,
        global: false,
        dataType: "json",
        success: function (response) {
            vote = response;
            userVote.isVote = false;
            delete userVote.voteId;
            $.get(ajaxRestaurantUrl, updateTableByData);
            // successNoty('common.saved');
        }
    });
}
