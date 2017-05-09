var form;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

function makeEditable() {
    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });

    // var token = $("meta[name='_csrf']").attr("content");
    // var header = $("meta[name='_csrf_header']").attr("content");
    // $(document).ajaxSend(function(e, xhr, options) {
    //     xhr.setRequestHeader(header, token);
    // });
}

// https://api.jquery.com/jquery.extend/#jQuery-extend-deep-target-object1-objectN
function extendsOpts(opts) {
    $.extend(true, opts,
        {
            "ajax": {
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "language": {
                "search": i18n["common.search"],
                "lengthMenu": i18n["common.show"],
                "info": i18n["common.info"],
                "loadingRecords": i18n["common.loading"],
                "paginate": {
                    "first": i18n["common.first"],
                    "last": i18n["common.last"],
                    "next": i18n["common.next"],
                    "previous": i18n["common.previous"]
                }
            },
            "initComplete": makeEditable
        }
    );
    return opts;
}

function add(title) {
    $('#modalTitle').html(title);
    form.find(":input").val("");
    $('#editRow').modal();
}

function updateRow(id) {
    $('#modalTitle').html(i18n[editTitleKey]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(
                key === "dateTime" ? formatDate(value) : value
            );
        });
        $('#editRow').modal();
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        global: false,
        success: function () {
            updateTable();
            successNoty('common.deleted');
        }
    });
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

function renderEditBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-success btn-circle" onclick="updateRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
    }
}


function renderDeleteBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-danger btn-circle" onclick="deleteRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function save() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        global: false,
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('common.saved');
        }
    });
}

