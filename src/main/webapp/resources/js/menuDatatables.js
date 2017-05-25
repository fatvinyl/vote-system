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
        renderModalFooter();
    }
    $('#editDishes').modal();
}

function drawRestaurantsSelector() {
    $.get("/ajax/profile/restaurants/all", function (data) {
        $('.modal-body').append(
            '<h5>' + i18n["restaurant.choose"] + ":" + '</h5>' +
            '<div class="form-group">' +
            '<div class="col-xs-7">' +
            '<select class="form-control" id="select_restaurant" onchange="if(this.value != null) { renderModalFooter();}"></select>' +
            '</div><br>' +
            '</div>');
        $("#select_restaurant").append("<option>---</option>");
        $.each(data, function (key, value) {
            $("#select_restaurant").append("<option value='" + value.id + "'>" + value.name + "</option>");
        });
    });
}

function renderModalFooter() {
    if (act == 'dish_edit') {
        renderDishesForm();
    } else if (act == 'rest_edit' || act == 'rest_create') {
        renderRestaurantForm()
    }
}

function renderDishesForm() {
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
                    "<a class='btn btn-success btn-circle glyphicon glyphicon-refresh' type='button' onclick='clickSaveDish(" + i + ")'></a>" +
                    "<a class='btn btn-danger btn-circle glyphicon glyphicon-trash' type='button' onclick='clickDeleteDish(" + i + ")'></a>" +
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
        "<a class='btn btn-success btn-circle glyphicon glyphicon-plus' type='button' onclick='clickSaveDish()'></a>" +
        "</div></form>");
}


function renderRestaurantForm() {
    document.getElementById('restaurant_form').innerHTML = '';
    var selector = document.getElementById("select_restaurant");
    var restaurantId = (selector != undefined) ? selector.options[selector.selectedIndex].value : "";
    var restaurantName = (selector != undefined) ? selector.options[selector.selectedIndex].text : "";
    var imgSrc = "/resources/images/" + ((restaurantId == '') ? "default_logo" : restaurantId) + ".png";
    var buttons = (restaurantId == '') ? "<a class='btn btn-success btn-circle glyphicon glyphicon-ok' type='button' onclick='clickSaveRestaurant()'></a>"
                                         : "<a class='btn btn-success btn-circle glyphicon glyphicon-refresh' type='button' onclick='clickSaveRestaurant()'></a>"+
                                           "<a class='btn btn-danger btn-circle glyphicon glyphicon-trash' type='button' onclick='clickDeleteRestaurant()'></a>";
    $('#restaurant_form').append("<form id='modal_restaurant' enctype='multipart/form-data'>" +
    "<input type='hidden' id='id' name='id' value='"+ restaurantId +"'>" +
        "<input type='text' class='form-control' name='name' value='"+ restaurantName +"' placeholder='Введите название ресторана'>" +
        "<div class='input-group'>" +
        "<div class='input-file-row-1'>"+
        "<div class='upload-file-container'>"+
        "<img id='imgPreview' src='"+imgSrc+"' alt='image' style='width:130px' class='img-circle'/>"+
        "<div class='upload-file-container-text'>	<span>Выберите Изоражение</span>"+
        "<input type='file' name='restaurantImage' accept='image/jpeg,image/png' onchange=getImgPreview(this) />"+
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

function clickSaveRestaurant() {
    var form = $("#modal_restaurant")[0];
    var data = new FormData(form);
    confirmNoty('confirm.save', createOrUpdateRestaurant, data);
}

function clickDeleteRestaurant() {
    var restaurantId = $('#select_restaurant').val();
    confirmNoty('confirm.restaurant.delete', deleteRestaurant, restaurantId);
}

function clickSaveDish(index) {
    var restaurantId = $('#select_restaurant').val();
    var dish = (index == undefined) ? $("#modal_menu").serialize() : $("#modal_menu" + index).serialize();
    confirmNoty('confirm.save', createOrUpdateDish, dish, restaurantId);
}


function clickDeleteDish(index) {
    var id = $("#modal_menu" + index + " #id").val();
    confirmNoty('confirm.delete', deleteDish, id);
}

function createOrUpdateRestaurant(data) {
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
            successNoty('common.saved');
            $(".close").click();
        }
    });
}

function deleteRestaurant(id) {
    $.ajax({
        url: "/ajax/profile/restaurants/" + id,
        type: 'DELETE',
        success: function () {
            successNoty('common.deleted');
            $(".close").click();
            renderDishesForm();
        }
    });
}

function createOrUpdateDish(dish, restaurantId) {
    $.post("/ajax/profile/dishes/" + restaurantId, dish, function (json) {
        successNoty('common.saved');
        renderDishesForm();
    })
}

function deleteDish(id) {
    $.ajax({
        url: "/ajax/profile/dishes/" + id,
        type: 'DELETE',
        success: function () {
            successNoty('common.deleted');
            renderDishesForm();
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

