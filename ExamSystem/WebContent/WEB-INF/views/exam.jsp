<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	

	

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body >
	<div class="panel panel-default" >
		<div class="panel-heading">
			<h3 class="panel-title text-center">试卷生成预览</h3>
		</div>
		<div class="panel-body" >
			<div id="toolbar" class="btn-group ">
				<div class="form-inline">
					<label for="subject" >选择科目</label>
					<select class="form-control" id="subject"  >
						<option value="-1">请选择</option>	
					</select>
					<button class="btn btn-info" id="getexam" >随机生成试卷</button>	
					<button class="btn btn-info" id="postexam" >添加到试卷库</button>					
				</div>
			</div>
		</div>
	</div>
	
	<div id="exambody" >
	
	</div >
</body>
<script type="text/javascript">

	$(document).ready(function() {
		$.ajax({
			url : "queryAllSubject",
			method : 'post',
			datatype:'json',
			data : {
				
			},
			success : function(data) {
				
				$.each(data.subjectList, function(index, subjectList) {
		            $("#subject").append(  //此处向select中循环绑定数据
		    				"<option value="+subjectList.id+">" + subjectList.name+ "</option>");
		        });
			}
		});
	});

	var msg ;
	$("#getexam").click(function() {
		$('#exambody').empty();
		$.ajax({
			url : "getQuestionsBySubject",
			method : 'post',
			datatype:'json',
			data : {
				subject_id :  $("#subject option:selected").val()
			},
			
			success:function(data){
				msg = data;
                var arr=data.questionList;
                var length = data.questionList.length;
                var c = 0;
                var index ;
                console.log(msg);
                 for(var i=0;i<length;i++){ 		
                 	var obj = arr[i];
                    var type_id = arr[i].questionType.questionType_id;
                     /* $('#exambody').prepend("<dl align='left'><dt>"+index+"、"+subject+"</dt><dd>A："
                    		+optionA+"</dd><dd>B："+optionB+"</dd><dd>C："
                    		+optionC+"</dd><dd>D："+optionD+"</dd></dl>");   */
                    	if(type_id == 1 && c == 0){
                    		   $('#exambody').append(" 一 。单选题（40分）");
                    		   index = 1;
    							c=1;    							
                    	}else if(type_id == 2 && c== 1){
                    		$('#exambody').append(" 二 。多选题（40分）");
                 		   index = 1;
                 		   c=2;   		  
                    	}else if(type_id == 5 && c== 2){
                    		$('#exambody').append(" 三 。简答题（20分）");
                    		index = 1;
                    		c=3;	
                    	}
                    		write(obj ,index)
	                    index = index +1;
                 
                }   
            }
		});	
	});
	function write(obj ,index){
		   var subject = obj.subject;
           var optionA=obj.optionA;
           var optionB=obj.optionB;
           var optionC=obj.optionC;
           var optionD=obj.optionD;
           var type_id = obj.questionType.questionType_id;
           if(type_id == 5){
        	   $('#exambody').append("<div class='form-group col-lg-12'><p>"+index+"、"+subject+"</p></div>");
           }else{
        	   $('#exambody').append("<div class='form-group col-lg-12'><p>"+index+"、"+subject+"</p></div>");
               $('#exambody').append("<div class='form-group col-lg-3'><p>选项A：<span >"+optionA+"</span></p></div>");
               $('#exambody').append("<div class='form-group col-lg-3'><p>选项B：<span >"+optionB+"</span></p></div>");
               $('#exambody').append("<div class='form-group col-lg-3'><p>选项C：<span >"+optionC+"</span></p></div>");
               $('#exambody').append("<div class='form-group col-lg-3'><p>选项D：<span >"+optionD+"</span></p></div>");
           }
		  
		
	}
	$("#postexam").click(function() {
		
		if (confirm("是否添加?")) {
			$.ajax({
				url : "saveExam",
				method : 'post',
				datatype:'json',
				contentType:"application/json",
				data:JSON.stringify(msg.questionList),
				
				success:function(data){
					  
	            }
			});	
		}else{}
		
		
	});

</script>

</html>