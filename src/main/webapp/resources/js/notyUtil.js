


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

    // http://tomcat.apache.org/tomcat-8.5-doc/changelog.html
    // RFC 7230 states that clients should ignore reason phrases in HTTP/1.1 response messages.
    // Since the reason phrase is optional, Tomcat no longer sends it (statusText).
    failedNote = noty({
        text: errorInfo.details.join("<br>"),
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
                } else if (callback === 'createOrUpdateUser') {
                    createOrUpdateUser(arg1, arg2);
                }  else if (callback === 'deleteUser') {
                    deleteUser(arg1);
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