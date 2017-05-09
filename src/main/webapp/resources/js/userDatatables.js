var ajaxUrl = 'ajax/admin/users/';
var datatableApi;
var editTitleKey ="users.edit";



function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: ajaxUrl + id,
        type: 'POST',
        data: 'enabled=' + enabled,
        success: function () {
            chkbox.closest('tr').toggleClass('disabled');
            successNoty(enabled ? 'common.enabled' : 'common.disabled');
        }
    });
}

// $(document).ready(function () {
$(function () {
    datatableApi = $('#datatable').DataTable(extendsOpts({
        "ajax": {
            "url": ajaxUrl
        },
        "columns": [
            {
                "data": "name",
                "render": function (data, type, row) {
                    return '<span class="table_bold_txt">' + data + '</span>';
                }
            },
            {
                "data": "email",
                "render": function (data, type, row) {
                    if (type == 'display') {
                        return '<a class="table_bold_txt" href="mailto:' + data + '">' + data + '</a>';
                    }
                    return data;
                }
            },
            {
                "data": "roles",
                "render": function (data, type, row) {
                    return '<span class="table_txt">' + data + '</span>';
                }
            },
            {
                "data": "enabled",
                "render": function (data, type, row) {
                    if (type == 'display') {
                        return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="enable($(this),' + row.id + ');"/>';
                    }
                    return data;
                }
            },
            {
                "data": "registered",
                "render": function (date, type, row) {
                    if (type == 'display') {
                        return '<span class="table_txt">' + date.substring(0, 10) + '</span>';
                    }
                    return date;
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
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).addClass("disabled");
            }
        }
    }));
});

function renderEditBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-success btn-circle" onclick="openUserEditModal(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
    }
}

function renderDeleteBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-danger btn-circle" onclick="clickDeleteUser(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function openUserEditModal(id) {
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

function openUserAddModal(title) {
    $('#modalTitle').html(title);
    form.find(":input").val("");
    $('#editRow').modal();
}

function clickSaveUser() {
    var data = form.serialize();
    confirmNoty('confirm.save', 'createOrUpdateUser', ajaxUrl, data);
}

function clickDeleteUser(id) {
    confirmNoty('confirm.delete', 'deleteUser', id, null);
}

function createOrUpdateUser(ajaxUrl, data) {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: data,
        // global: false,
        success: function () {
            $('#editRow').modal('hide');
            successNoty('common.saved');
            updateTable();
        }
    });
}

function deleteUser(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        global: false,
        success: function () {
            successNoty('common.deleted');
            updateTable();
        }
    });
}
