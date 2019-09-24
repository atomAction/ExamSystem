<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>

		<title>登录,注册页面</title>
		<link href="${pageContext.request.contextPath}/css/bootstrap2.0.min.css" rel="stylesheet" media="screen">
     <style type="text/css">
     	body{
     	margin: 0;
     	padding: 0; 
     	 font-family:"微软雅黑";
     	  background: url(img/bg.png); 		
     	}
     	#login{
     		width: 500px;
     		height: 250px;    		
     		position: absolute;
			left: 0;
			top: 0;
			right: 0;
			bottom: 0;
			margin: auto;

     	}
     		#login h{
     		    margin-left: 80px;
     		  font-size: 40px;
     		    color: white;
     		    text-align: center;
     		    margin-bottom: 80px;
     		    letter-spacing: 10px;
     		}
     		#login form{
     			margin-top: 20px;
     			
     		}
     		#radio{margin-left: 100px;}
     		.radio{color: white;
     		      float: left;
     		      margin-left: 40px;}
     		.control-group{ color: white;}
     		#btn{
     			
     			margin-top: 50px;
     		}
     		.btn{ width: 80px;
     		    margin-left: 120px;
     		   }
     </style>
	</head>
	<body>
		<div id="login">
			<h>在线考试系统登录</h>
			<form   class="form-horizontal" >
				 <div class="control-group">
				    <label class="control-label" for="inputEmail">用户名</label>
				    <div class="controls">
				      <input type="text" id="user_name" placeholder="请输入用户名" placeho name="user_name" id="username"  >
				    </div>
				  </div>
				 <div class="control-group">
					    <label class="control-label" for="inputPassword">密码</label>
					    <div class="controls">
					      <input type="password" id="user_pwd" placeholder="请输入登录密码" name="user_name" id="password" >
					    </div>
					  </div>
					  <div id="radio">
					  <label class="radio">
							学生<input type="radio" name="user_type"  value="student" checked="checked">					
							</label>
							<label class="radio">
							 教师<input type="radio" name="user_type"  value="teacher">					
							</label>
					  		<label class="radio">
							管理员<input type="radio" name="user_type"   value="admin" >
							</label>
					  </div>
				<div id="btn">
					 <button type="submit" class="btn" id="login_btn" onclick="Form(); return false;" >登录</button>
					<button type="reset" class="btn">取消</button>
				</div>			
			</form>	
		</div>
		
	</body>
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
	function Form() {
		var id=document.getElementById("user_name").value;
		var password=document.getElementById("user_pwd").value;
		if(id==null||id==""){
			alert("准考证号不能为空！");
			return false;
		}else if(password==null||password==""){
			alert("登录密码不能为空！");
			return false;
		}
		else{
			$.ajax({
				url : "login",
				method : 'post',
				contentType:'application/json;charset=utf-8',
				data :JSON.stringify( {
					user_name : $("#user_name").val(),
					user_pwd:$("#user_pwd").val(),
					user_type:$('input:radio[name="user_type"]:checked').val()			
				}),
				success : function(data) {
					console.log(data);
					if(data.statu == "error"){
						 alert(data.msg);
						 
					}
					else if(data.logintype == "student"){
						alert(data.msg);
						window.location.href = "gotoStudentindex";
					}else if(data.logintype == "teacher"){
						alert(data.msg);
						window.location.href = "gotoindex";
					}else {
						alert(data.msg);
						window.location.href = "gotoAdminIndex";
					}
					
				}
			});
		}
		
	};
</script>
</html>
