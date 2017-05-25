
var isClick = false;

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
        theme: 'relax',
        text: i18n[message],
        type: 'success',
        layout: 'bottomRight',
        animation: {
            open: {height: 'toggle'},
            close: {height: 'toggle'},
            easing: 'swing',
            speed: 800
        },
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    var errorInfo = $.parseJSON(jqXHR.responseText);

    failedNote = noty({
        theme: 'relax',
        text: errorInfo.details.join("<br>"),
        type: 'error',
        layout: 'bottomRight'
    });
}

function confirmNoty(message, callback, arg1, arg2) {
    if (!isClick) {
        isClick = true;
        noty({
            theme: 'relax',
            text: i18n[message],
            type: 'warning',
            layout: 'bottomRight',
            buttons: [
                {
                    addClass: 'btn btn-success btn-circle glyphicon glyphicon-ok', onClick: function ($noty) {
                    $noty.close();
                    callback(arg1, arg2);
                    isClick = false;
                }
                },
                {
                    addClass: 'btn btn-danger btn-circle glyphicon glyphicon-remove', onClick: function ($noty) {
                    $noty.close();
                    isClick = false;
                }
                }

            ]
        });
    }

}

