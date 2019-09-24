<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 引入bootstrap样式 -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<!-- 引入bootstrap-table样式 -->
	<link href="css/bootstrap-table.min.css" rel="stylesheet">
	
	<link rel="stylesheet"href="css/blue.css" />
	
	<!-- jquery -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/icheck.js"></script>
	
	<!-- bootstrap-table.min.js -->
	<script src="js/bootstrap-table.min.js"></script>
	<!-- 引入中文语言包 -->
	<script src="js/bootstrap-table-zh-CN.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">修改个人信息</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>姓名</label> <input type="text" class="form-control"
								id="admin_name">
						</div>
						<div class="form-group">
							<label>工号</label> <input type="text" class="form-control"
								id="admin_number">
						</div>
					
						<div class="form-group">
							<label>登录账号</label> <input type="text" class="form-control"
								id="user_number">
						</div>
						<div class="form-group">
							<label>登录密码</label> <input type="text" class="form-control"
								id="user_password">
						</div>
						
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="updateConfirmBtn" onclick="update()"> 提交更改</button>
				</div>
			</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$.ajax({
		url : "queryAdminInfo",
		method : 'post',
		datatype:'json',
		data : {
			
		},
		success : function(data) {
			
		
				$("#admin_number").val(data.admin.number);
				$("#admin_name").val(data.admin.name);
				$("#user_number").val(data.admin.user_id.user_name);
				$("#user_password").val(data.admin.user_id.user_pwd);
				
	       
		}
	});
});

function update() {
	if (confirm("是否更改?")) {
		$.ajax({
			url : "updateAdminInfo",
			data : {
				number : $("#admin_number").val(),
				name : $("#admin_name").val(),
				user_name : $("#user_number").val(),
				user_pwd :$("#user_password").val(),
			},
			success : function(data) {
				alert("更新成功！");
				//重新加载
				window.location.reload();
			
			}
		});
	} else {
	}
}
</script>
</html>