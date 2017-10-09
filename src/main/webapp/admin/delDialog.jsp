<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal modal-danger fade" id="modal-delete">
    <div class="modal-dialog">
        <div class="modal-content" style="margin-top: 180px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">删除确认</h4>
            </div>
            <div class="modal-body">
                <p>确认要删除&#34;<span id="delTitle"></span>&#34;吗?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-outline" data-objectId="0" data-srcUrl="" data-append="" onclick="delAction()" id="delBtn">确认</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
