function delDialogOpen(id,title,url) {
    $('#delTitle').text(title);
    $('#delBtn').data('objectId',id);
    $('#delBtn').data('srcUrl',url);
    $('#modal-delete').modal('show');
}
$(document).ajaxStart(function() { Pace.restart(); });
function delAction() {
    $('#modal-delete').modal('hide');
    var id=$('#delBtn').data('objectId');
    var url=$('#delBtn').data('srcUrl');
    $.ajax({
        url:url,
        dataType:'json',
        type:'post',
        data:{
            method:'deleteNewsType',
            id:id
        },
        error:function (XMLHttpRequest,textStatus,errorThrown) {
            showDangerAlert("请求失败！");
        },
        success:function (data) {
            if(data.stat==0){
                $("#newsTypeTableTr"+id).remove();
                showSuccessAlert(data.msg);
            }else {
             showDangerAlert(data.msg);
            }
        }
    });
}