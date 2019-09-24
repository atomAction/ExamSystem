<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>出题界面</title>
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<style type="text/css">
			body{
		margin: 0;
     	padding: 0; 
     	background-color:#FAFAFA;
			}
			.wrap{ width: 800px;
			height: 800ppx;
			
			position: absolute;
			left: 0;
			top: 0;
			right: 0;
			bottom: 0;
			margin: auto;}
			  h1{ text-align: center;
			  letter-spacing: 50px;}
			  #select{ width: 200px;
			 }
			 .btn{ margin-left: 100px;}
		</style>
	</head>
	<body >
		<div class="wrap">
		<form class="form-horizontal" role="form">
			<h1>试卷</h1>
		  <div class="form-group">
		    <label for="questionType" class="col-sm-2 control-label">问题类型</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="questionType" placeholder="请输入问题类型">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="subject" class="col-sm-2 control-label">题目</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="subject" placeholder="请输入题目">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="grade" class="col-sm-2 control-label">分值</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="grade" placeholder="请输入题目分值">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="answer" class="col-sm-2 control-label">请输入答案</label>		    
		  </div>
		   <div class="form-group">
		    <label for="optionA" class="col-sm-2 control-label">选项A：</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="optionA" placeholder="请输入选项A">
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="optionB" class="col-sm-2 control-label">选项B：</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="optionB" placeholder="请输入选项B">
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="optionC" class="col-sm-2 control-label">选项C：</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="optionC" placeholder="请输入选项C">
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="optionD" class="col-sm-2 control-label">选项D：</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="optionD" placeholder="请输入选项D">
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="answer" class="col-sm-2 control-label">单选题正确答案:</label>	
		     <div class="col-sm-10">
		     <select class="form-control"   id="select">
			  <option  id="optionA">A</option>
			  <option id="optionB">B</option>
			  <option id="optionC">C</option>
			  <option  id="optionD">D</option>
			</select>
			 </div>
		  </div>
		  <div class="form-group">
		    <label for="answer" class="col-sm-2 control-label">其他类型题答案：</label>
		    <div class="col-sm-10">
		      <textarea class="form-control" rows="3"></textarea>
		    </div>
		  </div>
		  
		  
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">提交</button>
		      <button type="reset"  class="btn btn-default">取消</button>
		    </div>
		  </div>
		</form>	
		</div>
	</body>
	<script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
   	<script type="text/javascript">
	</script>
</html>
