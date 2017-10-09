function showSuccessAlert(str) {
    $('#content').prepend('<div class="alert alert-success alert-dismissible" id="requestSuccess">\n' +
        '                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>\n' +
        '                <h4><i class="icon fa fa-check"></i> Alert!</h4>\n' + '<p></p>' +
        '                 </div>');
    $('#requestSuccess p').text(str);
    $('#requestSuccess').fadeOut(5000, function () {
        $(this).remove();
    });
}

function showDangerAlert(str) {
    $('#content').prepend('<div class="alert alert-danger alert-dismissible" id="requestFail">\n' +
        '                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>\n' +
        '                <h4><i class="icon fa fa-ban"></i> Alert!</h4>\n' +
        '    <p></p> </div>');
    $('#requestFail p').text(str);
    $('#requestFail').fadeOut(5000, function () {
        $(this).remove();
    });
}