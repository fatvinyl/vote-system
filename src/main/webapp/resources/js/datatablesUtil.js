var form;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

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
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "language": {
                "search": i18n["common.search"],
                "info": i18n["common.info"],
                "loadingRecords": i18n["common.loading"]
            },
            "initComplete": makeEditable
        }
    );
    return opts;
}






