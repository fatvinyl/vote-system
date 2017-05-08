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
//
// function formatDate(date) {
//     return date.replace('T', ' ').substr(0, 16);
// }

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


var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(message) {
    closeNoty();
    noty({
        text: i18n[message],
        type: 'success',
        layout: 'bottomRight',
        // speed: 2500,
        timeout: true

    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    var errorInfo = $.parseJSON(jqXHR.responseText);

    // http://tomcat.apache.org/tomcat-8.5-doc/changelog.html
    // RFC 7230 states that clients should ignore reason phrases in HTTP/1.1 response messages.
    // Since the reason phrase is optional, Tomcat no longer sends it (statusText).
    failedNote = noty({
        text: i18n['common.status'] + ': ' + jqXHR.status + "<br>" + errorInfo.cause + "<br>" + errorInfo.details.join("<br>"),
        type: 'error',
        layout: 'bottomRight'
    });
}

function confirmNoty(message, callback, arg1, arg2) {
    noty({
        text: i18n[message],
        type: 'warning',
        layout: 'bottomRight',
        buttons: [
            {
                addClass: 'btn btn-success btn-circle glyphicon glyphicon-ok', onClick: function ($noty) {
                $noty.close();
                if (callback === 'updateVote') {
                    updateVote(arg1, arg2)
                } else if (callback === 'deleteVote') {
                    deleteVote(arg1, arg2);
                } else if (callback === 'createOrUpdateRestaurant') {
                    createOrUpdateRestaurant(arg1);
                } else if (callback === 'deleteRestaurant') {
                    deleteRestaurant(arg1);
                } else if (callback === 'createOrUpdateDish') {
                    createOrUpdateDish(arg1, arg2);
                } else if (callback === 'deleteDish') {
                    deleteDish(arg1);
                }
            }
            },
            {
                addClass: 'btn btn-danger btn-circle glyphicon glyphicon-remove', onClick: function ($noty) {
                $noty.close();
            }
            }
        ]
    });

}
