<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<style type="text/css">
	#clock{
		font-family: 'Days One',"Microsoft YaHei", Arial,sans-serif;
		font-size: 2em;
	}
</style>
<head>
	<!-- 引入bootstrap样式 -->
	<link href="../css/bootstrap.min.css" rel="stylesheet"> 
	<!-- 引入bootstrap-table样式 -->
	<link href="${pageContext.request.contextPath}/css/bootstrap-table.min.css" rel="stylesheet">
	
	<link rel="stylesheet"href="../css/blue.css" />
	<link rel="stylesheet" type="text/css" href="../css/normalize.css" />
<link rel="stylesheet" type="text/css" href="../css/demo.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<!-- jquery -->
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script> 
	<script src="../js/icheck.js"></script>
	<style type="text/css">
	<style type="text/css">
	#clock{
		font-family: 'Days One',"Microsoft YaHei", Arial,sans-serif;
		font-size: 2em;
		margin-bottom: 0;
	}
	.starter-template {
		  padding: 40px 15px;
		  text-align: center;
		}
	.panel {
		  width: 400px;
		  margin-left: auto;
		  margin-right: auto;
		}
		#btn-reset {
		  margin-right: 10px;
		}
</style>

	

<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
		
			<!-- 	<div class="form-inline" id="select_exam">
					
					<label for="subject" >选择AB卷</label>
					<select class="form-control" id="examId"  >
						<option value="-1">请选择</option>	
							
					</select>
					<button class="btn btn-info" id="getexam" >开始考试</button>	
										
				</div> -->

	<div class="content bgcolor-8">
		<p class="center">距离考试结束：</p>
		<div id="clock"></div>
	</div>
		<form class="form-horizontal  well  wrap" role="form" id="formExam">
			<h1>试卷</h1>
			<div class="progress">
			  <div class="progress-bar progress-bar-striped active" id="prog" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
			    <span class="sr-only">90% Complete</span>
			  </div>
			</div>
			
			<div class="form-inline" >		
				<select class="form-control"  >
				 <option id="1"> 单选</option>
				 <option id="2">多选</option>
				 <option id="3">简答题</option>			  
				</select>
			</div>
			
			<div id="exambody" class="radio  form-group" style="min-height: 400px">				
				
			</div>
			<div id="button  form-group">
				<button type="button" class="btn btn-info" id="before_question" onclick="before()">上一题</button>
				<button type="button" class="btn btn-info" id="after_question" onclick="after()">下一题</button>
				<button type="button" class="btn btn-info"id="sbmitExam_bt" onclick="sbmitExam()">提交试卷</button>
			</div>
		</form>
		
	</body>
	<script type="text/javascript" src="../js/jquery.countdown.min.js"></script>
	<script type="text/javascript">
	
	$(document).ready(function() {
		$("#formExam").hide();
		var exam_id = getCookie("exam_id");
		var examGroup_id = getCookie("examGroup_id");
		examGroupId = examGroup_id;
		examId = exam_id;
		console.log(examGroupId);
		start();
		
		var twoDaysFromNow = new Date().valueOf() + 120 * 60 * 1000;
		$('#clock').countdown(twoDaysFromNow, function(event) {
		  var totalHours = event.offset.totalDays * 24 + event.offset.hours;
		  $(this).html(event.strftime(totalHours + ' 小时 %M 分钟 %S 秒'));
		});
		
		
	});
	var examGroupId;
	var examId;
	var arr ;
	var index;
	var map = {};
	var check;
	function start() {
		$("#formExam").show();
		$('#exambody').empty();
		$("#before_question").hide();
		$("#sbmitExam").hide();
				$.ajax({
					url : "getStudentExam",
					method : 'post',
					datatype:'json',
					data:{
						examId:examId
					},
					
					success:function(data){
						 arr = data.exam.questions;
						index = 0;
						var percent = (index+1)*(100/arr.length);
						$("#prog").css("width", percent + "%");
						writequestion(arr ,index );	
				
		            }
				});	
		};
		function after(){
			var type_id = arr[index].questionType.questionType_id;
			if(type_id ==1 ){
				check = $('input:radio[name="option"]:checked').val();
			}else if(type_id == 2){
				check = getduoanswer();
				console.log("111111111");
				console.log(check);
			}else if(type_id == 5){
				check = $('#other_answer').val();
			}
			 
			if(check == null){
				alert("你未选择答案"); 
				return false; };
			map[index] = check; 
			console.log(map);
			$("#before_question").show();
			$('#exambody').empty();
			index = index+1;
			var percent = (index+1)*(100/arr.length);
			if(index == arr.length-1 ){$("#after_question").hide();$("#sbmitExam").show();};
			$("#prog").css("width", percent + "%");
			writequestion(arr ,index );	
			$("input:radio[name='option'][value= "+map[index]+"]").attr("checked",'checked');
		}
		
		function before(){
			
			$("#after_question").show();
			$('#exambody').empty();
			index = index-1;
			var percent = (index+1)*(100/arr.length);
			if(index == 0 ){$("#before_question").hide();};
			$("#prog").css("width", percent + "%");
			writequestion(arr ,index );		
			
			$("input:radio[name='option'][value= "+map[index]+"]").attr("checked",'checked');
			
			//$('input:radio[name="option"]').attr("value","D");
		}
		
		function writequestion(arr ,index ){
			var type_id = arr[index].questionType.questionType_id;
			if(type_id == 1 ){	
				$('#exambody').append("</br><h4>一 。单选题（40分）</h4></br>");
				$('#exambody').append("</br><p>题目："+arr[index].subject+"</p></br>");
				$('#exambody').append("<label><p><br> <input type='radio' name='option' value='A' >"+arr[index].optionA+"</p></label></br>");
				$('#exambody').append("<label><p><br> <input type='radio' name='option' value='B' >"+arr[index].optionB+"</p></label></br>");
				$('#exambody').append("<label><p><br> <input type='radio' name='option' value='C' >"+arr[index].optionC+"</p></label></br>");
				$('#exambody').append("<label><p><br> <input type='radio' name='option' value='D' >"+arr[index].optionD+"</p></label>");
			}else if(type_id == 2){
				$('#exambody').append("</br><h4>二 。多选题（40分）</h4></br>"); 
				$('#exambody').append("</br><p>题目："+arr[index].subject+"</p></br>");
				$('#exambody').append("<label><p><br> <input type='checkbox' name='option' value='A' >"+arr[index].optionA+"</p></label></br>");
				$('#exambody').append("<label><p><br> <input type='checkbox' name='option' value='B' >"+arr[index].optionB+"</p></label></br>");
				$('#exambody').append("<label><p><br> <input type='checkbox' name='option' value='C' >"+arr[index].optionC+"</p></label></br>");
				$('#exambody').append("<label><p><br> <input type='checkbox' name='option' value='D' >"+arr[index].optionD+"</p></label>");
			}else if(type_id == 5){
				$('#exambody').append("</br><h4>三。简答题（20分）</h4></br>"); 
				$('#exambody').append("</br><p>题目："+arr[index].subject+"</p></br>");
				$('#exambody').append("<div class='col-sm-10'><textarea class='form-control' rows='3' id='other_answer'></textarea></div>");
				
			}
			
		}
	
		function getduoanswer(){
            var arry = new Array();
            $('input[name="option"]:checked').each(function(index1, element) {
                arry.push($(this).val());
            });
            var arrystr = arry.join('');
            console.log("选中值："+ arrystr);
            return arrystr;
        }
		
		function sbmitExam(){
			check = $('#other_answer').val();
			if(check == null){
				alert("你未选择答案"); 
				return false; };
			map[index] = check; 
			console.log(map);
			map[-1] = examId; 
			map[-2] = examGroupId;
			$("#before_question").show();	
			// var student = "<%=request.getSession().getAttribute("user")%>";   
			$.ajax({
				url : "submitAnswers",
				method : 'post',
				datatype:'json',
				contentType:'application/json;charset=utf-8',
				data : JSON.stringify({answerMapAnd : map}),
				success : function(data) {
					alert("你的选择题分数为："+data.singleScore+" <br>最终分数由老师批改简答题后可以查到")
					window.location.href = "toStudentScore";
				}
			});
		}
		
		//获取 cookie 值的函数,创建一个函数用户返回指定 cookie 的值
		 function getCookie(cname){
		     var name = cname + "=";
		     var ca = document.cookie.split(';');
		     for(var i=0; i<ca.length; i++) {
		         var c = ca[i].trim();
		         if (c.indexOf(name)==0) return c.substring(name.length,c.length);
		     }
		     return "";
		 }
	</script>
</html>