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
                    return '<img src="/resources/images/' + data + '.png" alt="image" style="width:90px" class="img-circle"/>';
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
    $('.modal-body').empty();
    form.find(":input").val("");
    $("#dishes_form").empty();
    $("#restaurant_form").empty();
    if (act == 'dish_edit') {
        $('#modalTitle').html(title + " " + $('#menuDate').val());
        drawRestaurantsSelector();
    } else if (act == 'rest_edit') {
        $('#modalTitle').html(title).val();
        drawRestaurantsSelector();
    } else if (act == 'rest_create') {
        $('#modalTitle').html(title).val();
        drawModalFooter();
    }
    $('#editDishes').modal();
}

function drawRestaurantsSelector() {
    $.get("/ajax/profile/restaurants/all", function (data) {
        $('.modal-body').append(
            '<h5>' + i18n["restaurant.choose"] + ":" + '</h5>' +
            '<div class="form-group">' +
            '<div class="col-xs-7">' +
            '<select class="form-control" id="select_restaurant" onchange="if(this.value != null) { drawModalFooter();}"></select>' +
            '</div><br>' +
            '</div>');
        $("#select_restaurant").append("<option>---</option>");
        $.each(data, function (key, value) {
            $("#select_restaurant").append("<option value='" + value.id + "'>" + value.name + "</option>");
        });
    });
}

function drawModalFooter() {
    if (act == 'dish_edit') {
        drawDishesForm();
    } else if (act == 'rest_edit' || act == 'rest_create') {
        drawRestaurantForm()
    }
}

function drawDishesForm() {
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


function drawRestaurantForm() {
    document.getElementById('restaurant_form').innerHTML = '';
    var selector = document.getElementById("select_restaurant");
    var restaurantId = (selector != undefined) ? selector.options[selector.selectedIndex].value : null;
    var restaurantName = (selector != undefined) ? selector.options[selector.selectedIndex].text : "";
    var imgSrc = "/resources/images/" + ((restaurantId == null) ? "default_logo" : restaurantId) + ".png";
    var buttons = (restaurantId == null) ? "<a class='btn btn-success btn-circle glyphicon glyphicon-ok' type='button' onclick='saveRestaurantRow()'></a>" :
                                           "<a class='btn btn-success btn-circle glyphicon glyphicon-refresh' type='button' onclick='saveRestaurantRow()'></a>"+
                                           "<a class='btn btn-danger btn-circle glyphicon glyphicon-trash' type='button' onclick='saveRestaurantRow()'></a>";
    $('#restaurant_form').append("<form id='modal_restaurant' enctype='multipart/form-data'>" +
    "<input type='hidden' id='id' name='id' value='"+ restaurantId +"'>" +
        "<input type='text' class='form-control' name='name' value='"+ restaurantName +"' placeholder='Введите название ресторана'>" +
        "<div class='input-group'>" +
        "<div class='input-file-row-1'>"+
        "<div class='upload-file-container'>"+
        "<img id='imgPreview' src='"+imgSrc+"' alt='image' style='width:130px' class='img-circle' />"+
        "<div class='upload-file-container-text'>	<span>Выберите Изоражение</span>"+
        "<input type='file' name='restaurantImage' onchange=getImgPreview(this) />"+
        "</div>"+
        "</div>"+
        "</div>"+
        "</div>" +
           buttons +
        "</form>");
}

function getImgPreview(par) {
    document.getElementById('imgPreview').src = window.URL.createObjectURL(par.files[0]);
}



var restaurant;

function setRestaurant() {
    var elem = document.getElementById("filt");
    var opt = document.querySelector("#select_restaurant option[value='" + elem.value + "']");
    restaurant = {"id": (opt == undefined) ? null : opt.dataset.value, "name": elem.value};
    if (restaurant.id == null) {
        confirmNoty('confirm.restaurant.create', 'createRestaurant', restaurant, null);
    } else {
        drawDishesForm();
    }
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



function createOrUpdateDish(dish, restaurantId) {
    $.post("/ajax/profile/dishes/" + restaurantId, dish, function (json) {
        successNoty('common.saved');
        drawDishesForm();
    })
}

function deleteDish(id) {
    $.ajax({
        url: "/ajax/profile/dishes/" + id,
        type: 'DELETE',
        success: function () {
            successNoty('common.deleted');
            drawDishesForm();
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
            drawDishesForm();
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


function saveRestaurantRow() {
    var form = $("#modal_restaurant")[0];

    var data = new FormData(form);
    // var fd = new FormData(document.getElementById("#modal_restaurant"));
    // var fd = new FormData($("#modal_restaurant")[0]);
    // fd.append( "file", $("#file")[0].files[0]);
    // fd.append( "text", $("#restName").val());
    createRestaurant(data);

    // var restaurant = $("#modal_restaurant").serialize();
    // if (restaurant.id == undefined) {
    //     // confirmNoty('confirm.dish.add', 'createOrUpdateDish', dish, restaurantId);
    //     createRestaurant(fd);
    //     // confirmNoty('confirm.dish.add', 'createOrUpdateDish', dish, restaurantId);
    // } else {
    //     // confirmNoty('confirm.dish.edit', 'createOrUpdateDish', dish, restaurantId);
    //     createRestaurant(fd);
    //     // confirmNoty('confirm.dish.edit', 'createOrUpdateDish', dish, restaurantId);
    // }
}

function createRestaurant(data) {
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/ajax/profile/restaurants/",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {

        }
    });
    // $.post("/ajax/profile/restaurants/", fd, function () {
    //     successNoty('restaurant.saved');
    //     drawRestaurantsSelector();
    //     drawDishesForm();
    // })
}