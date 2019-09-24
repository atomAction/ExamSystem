<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<title>登录界面</title>        
		<link rel="stylesheet" type="text/css" href="admin/layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="admin/css/login.css" />  
		<style type="text/css">
			
			.layui-form-label{
				margin-left: -50px;
			}
			.layui-input-block{
				margin-left: -50px;
			}
			.layui-form-item{ color: white;}
		</style>
	</head>
	<body >
		<div  class="m-login-bg"> 
		<div class="m-login">
			<h3>在线考试系统登录</h3>
			<div class="m-login-warp">
				<form class="layui-form" action="/login" method="post">
					<div class="layui-form-item">
						<input type="text" name="userName" required lay-verify="required"
							placeholder="请输入用户名" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-item">
						<input type="password" name="userPwd" required lay-verify="required"
							placeholder="请输入密码" autocomplete="off" class="layui-input">
					</div>
					
					 <div class="layui-form-item">
					    <label class="layui-form-label">角色</label>
					    <div class="layui-input-block">
					    学生  <input type="radio" name="character" value="student" title="学生">
					     教师 <input type="radio" name="character" value="teacher" title="教师">
					   管理员  <input type="radio" name="character" value="admin" title="管理员">
					      
					    </div>
					  </div>
					  
					
					
					<div class="layui-form-item m-login-btn">
						<div class="layui-inline">
							<button class="layui-btn layui-btn-warm"
								lay-filter="LAY-user-login-submit" id="login_btn">登录</button>
						</div>
						<div class="layui-inline">
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
			</div>
			
		</div>
	</div>

	</body> 
	<script src="layuiadmin/layui/layui.js"></script> 
	<script type="text/javascript">
			layui.use('form', function(){
			    var form = layui.form(); 
			    form.render();
			});
	</script>

    <script>
		layui.config({
			base : '/layuiadmin/' //静态资源所在路径
		}).extend({
			index : 'lib/index' //主入口模块
		}).use([ 'index', 'user' ], function() {
			var $ = layui.$,
				setter = layui.setter,
				admin = layui.admin,
				form = layui.form,
				router = layui.router(),
				search = router.search;
			form.render();
	
			//自定义验证规则
			form.verify({
				userName : function(value) {
					if (value.length < 5) {
						return '用户名至少得5个字符啊';
					}
				},
				userPwd : [ /(.+){5,12}$/, '密码必须6到12位' ],
			});
	
	
			$("#login_btn").click(function() {
				$.ajax({
					url : "login",
					method : 'post',
				//	contentType:'application/json;charset=utf-8',
					data : {
						user_name : $("#user_name").val(),
						user_pwd:$("#user_pwd").val(),
						user_type:$('input:radio:checked').val()
						
					},
					success : function(data) {
						console.log(data);
						if(data.statu == "error"){
							 alert(data.msg);
							 checkForm();
						}
						else if(data.statu == "succes"){
							alert(data.msg);
							window.location.href = "${pageContext.request.contextPath}/views/index.jsp";
						}
						
					}
				});
			});
	
		});
	</script>   
</html>