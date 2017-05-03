var ajaxUrl = "/ajax/profile/restaurants/";
var datatableApi;
var editTitleKey = "menu.edit";
var menuDate;

$(function () {
    datatableApi = $('#datatable').DataTable(extendsOpts({
        "ajax": {
            "url": ajaxUrl
        },
        "columns": [
            {
                "data": "id",
                "render": function (data, type, row) {
                    return '<img src="/resources/images/'+data+'.png" alt="image" style="width:80px"/>';
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

var act;

function openModal(title, action) {
    act = action;
    $('.modal-body').remove();
    form.find(":input").val("");
    $("#dishes_form").children().remove();
    if (act == 'dish_edit') {
        $('#modalTitle').html(title + " " + $('#menuDate').val());
        drawRestaurantsSelector();
    } else if (act == 'rest_edit') {
        $('#modalTitle').html(title).val();
        drawRestaurantsSelector();
    } else if (act == 'rest_create') {
        $('#modalTitle').html(title).val();
    }
    $('#editDishes').modal();
}

var restaurant;

function setRestaurant() {
    var elem = document.getElementById("filt");
    var opt = document.querySelector("#select_restaurant option[value='" + elem.value + "']");
    restaurant = {"id": (opt == undefined) ? null : opt.dataset.value, "name": elem.value};
    if (restaurant.id == null) {
        confirmNoty('confirm.restaurant.create', 'createRestaurant', restaurant, null);
    } else {
        drawDishes();
    }
}

function drawModalFooter() {
    if (act == 'dish_edit'){
        drawDishes();
    } else if (act == 'rest_edit') {
        drawRestaurantEdit()
    }
}

function drawRestaurantEdit() {
    document.getElementById('restaurant_form').innerHTML = '';
    var div = $('#restaurant_form');
    var restaurantId = $('#select_restaurant').val();
}

function drawDishes() {
    document.getElementById('dishes_form').innerHTML = '';
    var div = $('#dishes_form');
    var restaurantId = $('#select_restaurant').val();
    if (restaurantId != null) {
        $.get("/ajax/profile/dishes/" + menuDate.serialize().substring(9) + "&" + restaurantId, function (data) {
            for (i = 0; i < data.length; i++) {
                var dish = data[i];
                div.append("<form id='modal_menu" + i + "'>" +
                    "<input type='hidden' id='id' name='id' value='" + dish.id + "' >" +
                    "<input type='hidden' id='date' name='date' value='" + dish.date + "' >" +
                    "<div class='form-group'>" +
                    "<div class='col-xs-7'>" +
                    "<input type='text' class='form-control' name='name' value='" + dish.name + "'>" +
                    "</div>" +
                    "<div class='col-xs-3'>" +
                    "<input type='text' class='form-control' name='price' value='" + dish.price + "'>" +
                    "</div>" +
                    "<a class='btn btn-success btn-circle glyphicon glyphicon-refresh' type='button' onclick='saveDishRow(" + i + ")'></a>" +
                    "<a class='btn btn-danger btn-circle glyphicon glyphicon-trash' type='button' onclick='deleteDishRow(" + i + ")'></a>" +
                    "</div></form>");
            }
        });
    }
    div.append("<form id='modal_menu' >" +
        "<input type='hidden' id='id' name='id' value='' >" +
        "<input type='hidden' id='date' name='date' value='" + $('#menuDate').val() + "' >" +
        "<div class='form-group'>" +
        "<div class='col-xs-7'>" +
        "<input type='text' class='form-control' name='name' placeholder='Добавьте Блюдо'>" +
        "</div>" +
        "<div class='col-xs-3'>" +
        "<input type='text' class='form-control' name='price' placeholder='Цена'>" +
        "</div>" +
        "<a class='btn btn-success btn-circle glyphicon glyphicon-plus' type='button' onclick='saveDishRow()'></a>" +
        "</div></form>");
}

// function drawRestaurant

function saveDishRow(index) {
    var dish;
    var restaurantId = $('#select_restaurant').val();
    if (index == undefined) {
        dish = $("#modal_menu").serialize();
        confirmNoty('confirm.dish.add', 'createOrUpdateDish', dish, restaurantId);
    } else {
        dish = $("#modal_menu" + index).serialize();
        confirmNoty('confirm.dish.edit', 'createOrUpdateDish', dish, restaurantId);
    }
}

function deleteDishRow(index) {
    var id = $("#modal_menu" + index + " #id").val();
    confirmNoty('confirm.dish.delete', 'deleteDish', id, null);
}

function deleteRestaurantRow() {
    var restaurantId = getRestaurantId();
    confirmNoty('confirm.restaurant.delete', 'deleteRestaurant', restaurantId, null);
}

function drawRestaurantsSelector() {
    $.get("/ajax/profile/restaurants/all", function (data) {
        $("option").remove();
            $('#restaurant_modal_body').append('<div class="modal-body">' +
                '<h5>' + i18n["restaurant.title"] + ":" + '</h5>' +
                '<div class="form-group">' +
                '<div class="col-xs-7">' +
                '<select class="form-control" id="select_restaurant" onchange="if(this.value != null) { drawModalFooter();}"></select>' +
                '</div><br></div></div>');
            $("#select_restaurant").append("<option>---</option>");
            $.each(data, function (key, value) {
                $("#select_restaurant").append("<option value='" + value.id + "'>" + value.name + "</option>");
            });
    });
}

function createRestaurant(restaurant) {
    $.post("/ajax/profile/restaurants/", restaurant, function (json) {
        successNoty('restaurant.saved');
        drawRestaurantsSelector();
        drawDishes();
    })
}

function createOrUpdateDish(dish, restaurantId) {
    $.post("/ajax/profile/dishes/" + restaurantId, dish, function (json) {
        successNoty('common.saved');
        drawDishes();
    })
}

function deleteDish(id) {
    $.ajax({
        url: "/ajax/profile/dishes/" + id,
        type: 'DELETE',
        success: function () {
            successNoty('common.deleted');
            drawDishes();
        }
    });
}

function deleteRestaurant(id) {
    $.ajax({
        url: "/ajax/profile/restaurants/" + id,
        type: 'DELETE',
        success: function () {
            successNoty('common.deleted');
            form.find(":input").val("");
            drawDishes();
        }
    });
}

$(".close").click(function () {
    filterTable();
});

function filterTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "byDate",
        data: $("#filter").serialize(),
        success: updateTableByData
    });
}

menuDate = $('#menuDate').val(function () {
    var date = new Date();
    var mm = date.getMonth() + 1; // getMonth() is zero-based
    var dd = date.getDate();

    return (date.getFullYear() + '-' +
    (mm > 9 ? '' : '0') + mm + '-' +
    (dd > 9 ? '' : '0') + dd);
});

menuDate.datetimepicker({
    timepicker: false,
    format: 'Y-m-d',
    formatDate: 'Y-m-d',
    onSelectDate: filterTable
});



