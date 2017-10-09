function editDialog(id,title,url) {
   $("#editNewsTypeId").text(id);
   $("#editNewsTypeName").attr('placeholder',title);
   $("#editBtn").data("objectId",id);
   $("#editBtn").data("srcUrl",url);
   $("#modal-info").modal('show');
}
$(document).ajaxStart(function() { Pace.restart(); });
function editAction() {
   $("#modal-info").modal("hide");
    var id=$('#editBtn').data('objectId');
    var name=$('#editNewsTypeName').val();
    alert(name);
    var url=$('#editBtn').data('srcUrl');
    $.ajax({
        url:url,
        dataType:'json',
        type:'post',
        data:{
            method:'editNewsType',
            id:id,
            name:name
        },
        error:function (XMLHttpRequest,textStatus,errorThrown) {
            showDangerAlert("请求失败！");
        },
        success:function (data) {
            if(data.stat==0){
                $("#newsTypeTableTr"+id+" td:nth-child(2)").text(name);
                showSuccessAlert(data.msg);
            }else {
                showDangerAlert(data.msg);
            }
        }
    });
}