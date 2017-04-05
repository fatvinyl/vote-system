var ajaxRestaurantUrl = "ajax/profile/restaurants/";
var ajaxVoteUrl = "/ajax/profile/votes/";
var datatableApi;
var editTitleKey = "meals.edit";

// $(document).ready(function () {
// function clearFilter() {
//     $("#filter")[0].reset();
//     $.get(ajaxUrl, updateTableByData);
// }

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxRestaurantUrl,
        success: updateTableByData
    });
}

$(function () {
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
                        return '';
                    } else {
                        return data;
                    }

                }
            },
            {
                "render": renderVoteBtn,
                "defaultContent": "",
                "orderable": false

            },
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
    }));
});

function renderVoteBtn(data, type, row) {
    // if (type == 'display') {
    return '<a class="btn btn" onclick="vote(' + row.id + ', ' + ((row.vote == undefined) ? null : '{id:' + row.vote.id + ', amount:' + row.vote.amount + '}' ) + ');">' +
        '<span class="btn btn-success" aria-hidden="true">Vote</span></a>';
    // }
}

function vote(restaurntId, vote) {
    if (vote == null) {
        saveVote(restaurntId)
    } else {
        var date = new Date().toLocaleDateString().split('.');
        vote.date = date[2] + '-' + date[1] + '-' + date[0];
        incrementVote(vote, vote.id, restaurntId);
    }
}

function saveVote(restaurntId) {
    $.ajax({
        type: "POST",
        url: ajaxVoteUrl + restaurntId,
        success: function () {
            $.get(ajaxRestaurantUrl, updateTableByData);
            // successNoty('common.saved');
        }
    });
}

function incrementVote(vote, voteId, restaurntId) {
    $.ajax({
        type: "POST",
        url: ajaxVoteUrl + "increment/" + voteId + "&" + restaurntId,
        data: vote,
        success: function () {
            $.get(ajaxRestaurantUrl, updateTableByData);
            // successNoty('common.saved');
        }
    });
}

function decrementVote(vote, voteId, restaurntId) {
    $.ajax({
        type: "POST",
        url: ajaxVoteUrl + "decrement/" + voteId + "&" + restaurntId,
        data: vote,
        success: function () {
            $.get(ajaxRestaurantUrl, updateTableByData);
            // successNoty('common.saved');
        }
    });
}