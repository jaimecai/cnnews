<%--
  Created by IntelliJ IDEA.
  User: jaimecai
  Date: 17-9-25
  Time: 下午2:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal modal-info fade" id="modal-info">
    <div class="modal-dialog">
        <div class="modal-content" style="margin-top: 180px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑新闻类型</h4>
            </div>
            <div class="modal-body">
                <div class="input-group">
                    <span class="input-group-addon" id="editNewsTypeId"></span>
                    <input type="text" class="form-control" placeholder="" id="editNewsTypeName">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-outline" data-objectId="0" data-srcUrl=""  onclick="editAction()"
                        id="editBtn">确认
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>