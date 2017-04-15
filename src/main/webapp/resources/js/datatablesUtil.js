var form;

function makeEditable() {
    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

// https://api.jquery.com/jquery.extend/#jQuery-extend-deep-target-object1-objectN
function extendsOpts(opts) {
    $.extend(true, opts,
        {
            "ajax": {
                "url": ajaxUrl,
                "dataSrc": ""
            },
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

function formatDate(date) {
    return date.replace('T', ' ').substr(0, 16);
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('common.deleted');
        }
    });
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}


var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key) {
    closeNoty();
    noty({
        text: i18n[key],
        type: 'success',
        layout: 'bottomRight',
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
        text: i18n['common.status'] + ': ' + jqXHR.status + "<br>"+ errorInfo.cause + "<br>" + errorInfo.details.join("<br>"),
        type: 'error',
        layout: 'bottomRight'
    });
}



