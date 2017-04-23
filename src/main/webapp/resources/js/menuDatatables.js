var ajaxUrl = "/ajax/profile/restaurants/";
var datatableApi;
var editTitleKey ="users.edit";

function filterTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "byDate",
        data: $("#filter").serialize(),
        success: updateTableByData
    });
}


var menuDate = $('#menuDate').val(function() {
    var date = new Date();
    var mm = date.getMonth() + 1; // getMonth() is zero-based
    var dd = date.getDate();

    return (date.getFullYear() + '-' +
        (mm>9 ? '' : '0') + mm + '-' +
        (dd>9 ? '' : '0') + dd);
});

menuDate.datetimepicker({
    timepicker: false,
    format: 'Y-m-d',
    formatDate: 'Y-m-d'
});

$(function () {

    datatableApi = $('#datatable').DataTable(extendsOpts({
        "ajax": {
            "url": ajaxUrl
        },
        "columns": [
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
                        result += data[x].dishName + "<br>";
                    }
                    return '<span class="table_dishes">' + result + '</span>';
                }

            },
            {
                "data": "menu",
                "render": function (data, type, row) {
                    var result = "";
                    for (x in data) {
                        result += data[x].price.replace(",", "-") + " ₽" + "<br>";
                    }
                    return '<span class="table_dishes">' + result + '</span>';
                }

            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    }));
});


