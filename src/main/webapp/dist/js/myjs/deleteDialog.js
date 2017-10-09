function delDialog(id,title,append,url) {
    $('#delTitle').text(title);
    $('#delBtn').data('objectId',id);
    $('#delBtn').data('srcUrl',url);
    $('#delBtn').data('append',append);
    $('#modal-delete').modal('show');
}
$(document).ajaxStart(function() { Pace.restart(); });
function delAction() {
    $('#modal-delete').modal('hide');
    var id=$('#delBtn').data('objectId');
    var url=$('#delBtn').data('srcUrl');
    var append=$('#delBtn').data('append');
    $.ajax({
        url:url,
        dataType:'json',
        type:'post',
        data:{
            append:append,
            method:'delete',
            id:id
        },
        error:function (XMLHttpRequest,textStatus,errorThrown) {
            showDangerAlert("请求失败！");
        },
        success:function (data) {
            if(data.stat==0){
                $("#"+id).remove();
                showSuccessAlert(data.msg);
            }else {
             showDangerAlert(data.msg);
            }
        }
    });
}