var ajaxUrl = "ajax/profile/restaurants/";
var datatableApi;
var editTitleKey = "meals.edit";

// $(document).ready(function () {
// function clearFilter() {
//     $("#filter")[0].reset();
//     $.get(ajaxUrl, updateTableByData);
// }
//
// function updateTable() {
//     $.ajax({
//         type: "POST",
//         url: ajaxUrl + "filter",
//         data: $("#filter").serialize(),
//         success: updateTableByData
//     });
// }

$(function () {
    datatableApi = $('#datatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "restaurantName"
            },
            {
                "data": "menu",
                "render": function ( data, type, row ) {
                    var result="";
                    for (x in data) {
                        result += data[x].dishName + "<br>";
                    }
                    return result;
                }

            },
            {
                "data": "menu",
                "render": function ( data, type, row ) {
                    var result="";
                    for (x in data) {
                        result += data[x].price + "<br>";
                    }
                    return result;
                }

            },
            {
                "data": "vote.amount",
                "render": function ( data, type, row ) {
                    if (data == null) {
                        return 0
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