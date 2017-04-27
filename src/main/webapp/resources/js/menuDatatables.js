var ajaxUrl = "/ajax/profile/restaurants/";
var datatableApi;
var editTitleKey ="menu.edit";
var restaurant;

function openModal(title) {
    $('#modalTitle').html(title);
    getAllRestaurants();
    $('#editRow').modal();
    // document.getElementById("filt").oninput = function(){
    //     var inp = this.value;
    //     var opt=document.querySelector("#dl_continents option[value='"+inp+"']");
    // }
// }
    // form.find(":input").val("");
    // $('#editRow').modal();
}

function getAllRestaurants() {
    $.get("/ajax/profile/restaurants/all", function (data) {
        $("option").remove();
        $.each(data, function (key, value) {
            $("#dl_continents").append("<option data-value='" + value.id + "' value='" + value.name + "'/>");
        });
    });
}

function getRestaurant() {
    var elem = document.getElementById("filt");
    var opt=document.querySelector("#dl_continents option[value='"+elem.value+"']");
    restaurant= {"id" : (opt==undefined) ? null : opt.dataset.value, "name" : elem.value};
    if (restaurant.id==null) {
        $.post("/ajax/profile/restaurants/", restaurant, function (json) {

        })
    }
    getAllDishes();
}

function getAllDishes() {
    document.getElementById('dishes').innerHTML = '';
    var div = $('#dishes');
    $.get("/ajax/profile/dishes/" + menuDate.serialize().substring(9) + "&" + restaurant.id, function (data) {
        for (i = 0; i < data.length; i++) {
            var dish = data[i];
            div.append( "<input type='hidden' id='id' name='id' value='" + dish.id + "' >" +
                        "<div class='form-group'>" +
                        "<div class='col-xs-7'>" +
                        "<input type='text' class='form-control' name='dishName' value='" + dish.dishName + "' placeholder='Блюдо'>" +
                    "</div>" +
                    "<div class='col-xs-3'>" +
                    "<input type='text' class='form-control' name='price' value='" + dish.price + "' placeholder='Цена'>" +
                    "</div>" +
                "<a class='btn btn-success btn-circle glyphicon glyphicon-pencil' type='button' onclick='saveDish()'></a>" +
                "<a class='btn btn-danger btn-circle glyphicon glyphicon-remove' type='button' onclick='deleteDish()'></a>" +
                    "</div>");
        }
    })
    div.append( "<input type='hidden' id='id' name='id' value='' >" +
        "<div class='form-group'>" +
        "<div class='col-xs-7'>" +
        "<input type='text' class='form-control' name='dishName' placeholder='Добавьте Блюдо'>" +
        "</div>" +
        "<div class='col-xs-3'>" +
        "<input type='text' class='form-control' name='price' placeholder='Цена'>" +
        "</div>" +
        "<a class='btn btn-success btn-circle glyphicon glyphicon-ok' type='button' onclick='saveDish()'></a>" +
        "</div>");
}


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
                        result += data[x].price + " ₽" + "<br>";
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


