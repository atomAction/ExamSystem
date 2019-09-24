<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>


</head>
<body >
<div align="center" style="padding-top: 20px;" >
		<form action="login" method="post" onsubmit="return checkForm()">
		<table  width="1004" height="584" background="image/login.jpg" >
			<tr height="200">
				<td colspan="4"></td>
			</tr>
			<tr height="10">
				<td width="68%"></td>
				<td width="10%"><label>准考证号：</label></td>
				<td><input type="text" id="user_name" name="user_name" value="${student.id }"/></td>
				<td width="30%"></td>
			</tr>
			<tr height="10">
				<td width="40%"></td>
				<td width="10%"><label>登录密码：</label></td>
				<td><input type="password" id="user_pwd" name="user_pwd" value="${student.password }"/></td>
				<td width="30%"></td>
			</tr>
			<tr height="10">
				<td width="40%"></td>
				<td width="10%"><label>登录类型：</label></td>
				<td><input type="password" id="user_type" name="student.password" value="${student.password }"/></td>
				<td width="30%"></td>
			</tr>
			<tr height="10">
				<td width="40%"></td>
				<td width="10%"><button id="login_btn" class="btn btn-primary" type="button">登录</button></td>
				<td><button class="btn btn-primary" type="button"  onclick="resetValue()">重置</button></td>
				<td width="30%"></td>
			</tr>
			<tr >
				<td></td>
			</tr>
		</table>
		</form>
	</div>
</body>


	
<script type="text/javascript">
	function checkForm(){
		var id=document.getElementById("id").value;
		var password=document.getElementById("password").value;
		if(id==null||id==""){
			alert("准考证号不能为空！");
			return false;
		}
		if(password==null||password==""){
			alert("登录密码不能为空！");
			return false;
		}
		return true;
	}
	
	function resetValue(){
		document.getElementById("id").value="";
		document.getElementById("password").value="";
	}
	
	
	$("#login_btn").click(function() {
		$.ajax({
			url : "login",
			method : 'post',
		//	contentType:'application/json;charset=utf-8',
			data : {
				user_name : $("#user_name").val(),
				user_pwd:$("#user_pwd").val(),
				user_type:$("#user_type").val()
				
			},
			success : function(data) {
				console.log(data);
				if(data.statu == "error"){
					 alert(data.msg);
					 checkForm();
				}
				else if(data.statu == "succes"){
					alert(data.msg);
					window.location.href = "gotoindex";
				}
				
			}
		});
	});
</script>
</html>
