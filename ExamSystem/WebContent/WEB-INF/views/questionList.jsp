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
<body style='overflow:scroll;overflow-y:hidden'>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title text-center">班级管理</h3>
		</div>
		<div class="panel-body">

			<div id="toolbar" class="btn-group">
				<div class="form-inline">
				<button id="btn_save" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>新增
				</button>
				<button id="btn_delete" type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
				</button>
					<select class="form-control" id="selectForm"  >
						<option value="id">ID</option>
						<option value="type">问题类型</option>
					</select>
					<input class="form-control" id="searchText"  type="text" placeholder="请输入搜索内容"></input>
					<button class="btn btn-info" id="searchBtn" >搜索</button>					
				</div>
			</div>

			<table data-toggle="table" id="table" data-height="650"
				data-classes="table table-hover" data-striped="true"
				data-pagination="true" data-side-pagination="server"
				data-show-refresh="true" data-show-toggle="true"
				data-show-columns="true" data-toolbar="#toolbar" >
				<thead>
					<tr>
						<th data-field="state" data-checkbox='ture'></th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<!-- 编辑用户模态框（Modal） -->
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">出 题</h4>
				</div>
		<div class="wrap">			
		<form class="form-horizontal" role="form">		
		  <div class="form-group">
		    <label for="questionType" class="col-sm-2 control-label">问题类型</label>
		    <div class="col-sm-10">
		      <select  class="form-control" id="selecttype">
								  <option value="-1">请选择...</option>
								  <option value="1">单选题</option>
								  <option value="2">多选题</option>
								  <option value="3">判断题</option>
								  <option value="4">填空题</option>
								  <option value="5">简答题</option>
								  <option value="6">程序阅读</option>
				</select> 
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
		  <div id="dananswer">
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
		   <div class="form-group" id="dan">
		    <label for="answer" class="col-sm-2 control-label">正确答案:</label>	
		     <div class="col-sm-10">
		     <select class="form-control"   id="answer">
			  <option  value="optionA">A</option>
			  <option id="optionB">B</option>
			  <option id="optionC">C</option>
			  <option  id="optionD">D</option>
			</select>
			 </div>
		  </div>
		  
		</div>
		  <div class="form-group"  id="otheranswer">
		  <label for="saveother_answer" class="col-sm-2 control-label">正确答案:</label>
		    <div class="col-sm-10">
		      <textarea class="form-control" rows="3" id="other_answer"></textarea>
		    </div>
		  </div>
		  <div class="form-group" >
		  	<label for="saveother_answer" class="col-sm-2 control-label">科目及对应知识点:</label>
			      <div class="form-inline">
				     <select class="form-control"   id="subjectname">
					  <option  value="-1">请选择。。</option>
					</select>
					<select class="form-control"   id="point">
					  <option  value="-1">请选择。。</option>
					</select>
				 </div>
				
		  </div>
		</form>	
		<div class="modal-footer">
		
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					
					<button type="button" class="btn btn-primary" id="updateConfirmBtn">添加</button>
				</div>
		</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- 添加用户模态框（Modal） -->
	<div class="modal fade" id="saveModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">出 题</h4>
				</div>
		<div class="wrap">			
		<form class="form-horizontal" role="form">		
		  <div class="form-group">
		    <label for="questionType" class="col-sm-2 control-label">问题类型</label>
		    <div class="col-sm-10">
		      <select  class="form-control" id="saveselecttype">
								  <option value="-1">请选择...</option>
								  <option value="1">单选题</option>
								  <option value="2">多选题</option>
								  <option value="3">判断题</option>
								  <option value="4">填空题</option>
								  <option value="5">简答题</option>
								  <option value="6">程序阅读</option>
				</select> 
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="subject" class="col-sm-2 control-label">题目</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="savesubject" placeholder="请输入题目">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="grade" class="col-sm-2 control-label">分值</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="savegrade" placeholder="请输入题目分值">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="answer" class="col-sm-2 control-label">请输入答案</label>		    
		  </div>
		  <div id="savedananswer">
		   <div class="form-group">
		    <label for="optionA" class="col-sm-2 control-label">选项A：</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="saveoptionA" placeholder="请输入选项A">
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="optionB" class="col-sm-2 control-label">选项B：</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="saveoptionB" placeholder="请输入选项B">
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="optionC" class="col-sm-2 control-label">选项C：</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="saveoptionC" placeholder="请输入选项C">
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="optionD" class="col-sm-2 control-label">选项D：</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="saveoptionD" placeholder="请输入选项D">
		    </div>
		  </div>
		   <div class="form-group" id="savedan">
		    <label for="answer" class="col-sm-2 control-label">正确答案:</label>	
		     <div class="col-sm-10">
		     <select class="form-control"   id="saveanswer">
			  <option  value="optionA">A</option>
			  <option id="optionB">B</option>
			  <option id="optionC">C</option>
			  <option  id="optionD">D</option>
			</select>
			 </div>
		  </div>
		  
		</div>
		  <div class="form-group"  id="saveotheranswer">
		  <label for="saveother_answer" class="col-sm-2 control-label">正确答案:</label>
		    <div class="col-sm-10">
		      <textarea class="form-control" rows="3" id="saveother_answer"></textarea>
		    </div>
		  </div>
		  <div class="form-group" >
		  	<label for="saveother_answer" class="col-sm-2 control-label">科目及对应知识点:</label>
			      <div class="form-inline">
				     <select class="form-control"   id="savesubjectname">
					  <option  value="-1">请选择。。</option>
					</select>
					<select class="form-control"   id="savepoint">
					  <option  value="-1">请选择。。</option>
					</select>
				 </div>
				
		  </div>
		</form>	
		<div class="modal-footer">
		
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					
					<button type="button" class="btn btn-primary" id="saveConfirmBtn">添加</button>
				</div>
		</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>


<script type="text/javascript">

	$(document).ready(function() {
		$("button[name='toggle']").height(20);
		$("button[name='refresh']").height(20);
		$("#dananswer").hide();
		$("#otheranswer").hide();
		$("#updateModal").modal('hide');
		$("#saveModel").modal('hide');
		$("#FileModal").modal('hide');
	});


	function del(id) {
		if (confirm("是否删除?")) {
			$.ajax({
				url : "deleteQuestionById",
				data : {
					"id" : id
				},
				success : function(data) {
					alert("删除成功！");
					//重新加载表格
					$("#table").bootstrapTable("refresh");
				}
			});
		} else {
		}
	}

	//编辑按钮点击事件
	var idGlobal = 0;
	function edit(obj) {		
		$("#updateModal").modal('show');
		idGlobal = obj.id;
		//初始化用户信息
		//清楚输入框信息
		var typeId = obj.questionType.questionType_id;
		console.log(typeId);
		if(typeId==1){
			$("#dananswer").show();
			$("#dan").show();
			$("#otheranswer").hide();
			$("#subject").val(obj.subject);
			$("#grade").val(obj.questionType.grade);
			$("#selecttype ").val(typeId);
			$("#answer").val(obj.answer);
			$("#grade").val(obj.questionType.grade);
			$("#optionA").val(obj.optionA);
			$("#optionB").val(obj.optionB);
			$("#optionC").val(obj.optionC);
			$("#optionD").val(obj.optionD);
		}else if(typeId==2){
			$("#dananswer").show();
			$("#dan").hide();
			$("#otheranswer").show();
			$("#subject").val(obj.subject);
			$("#grade").val(obj.questionType.grade);
			$("#selecttype ").val(typeId);
			$("#answer").val(obj.answer);
			$("#grade").val(obj.questionType.grade);
			$("#optionA").val(obj.optionA);
			$("#optionB").val(obj.optionB);
			$("#optionC").val(obj.optionC);
			$("#optionD").val(obj.optionD);
		}else {
			$("#dananswer").hide();
			$("#otheranswer").show();
			$("#subject").val(obj.subject);
			$("#grade").val(obj.questionType.grade);
			$("#selecttype ").val(typeId);
			$("#answer").val(obj.answer);
			$("#grade").val(obj.questionType.grade);
		}
		
		
	//	$("#selecttype").val(obj.subject);
		
		if(typeId == 1){
			
		}else{$("#other_answer").val(obj.answer);}
		$("#subjectname option:gt(0)").remove();
		//初始化科目信息
		$.ajax({
			url : "queryAllSubject",
			method : 'post',
			datatype:'json',
			data : {			
			},
			success : function(data) {			
				$.each(data.subjectList, function(index, subjectList) {	
		            $("#subjectname").append(  //此处向select中循环绑定数据
		    				"<option value="+subjectList.id+">" + subjectList.name+ "</option>");
		        });
			}
		});	
	}
	
	//编辑模态框下确认按钮的点击事件
	$("#updateConfirmBtn").click(function() {
		$("#updateModal").modal('hide');
		var type_id = $("#selecttype option:selected").val();
		if(type_id == -1){
			alert("请输入正确的题型");
			return false;
		}else if(type_id == 1){
			var answer = $("#answer").val();
			console.log(answer);
		}else{
			var answer = $("#other_answer").val();
			console.log(answer);
		}
		$.ajax({
			url : "updateQuestion",
			method : 'post',
			datatype:'json',
			data : {
				id : idGlobal ,
				subject : $("#subject").val(),
				optionA : $("#optionA").val(),
				optionB : $("#optionB").val(),
				optionC : $("#optionC").val(),
				optionD : $("#optionD").val(),
				point_id : $("#point").val(),
				answer : answer,
				questionType_id : type_id			
			},
			success : function() {
				alert("添加成功！");
				//重新加载表格
				$("#table").bootstrapTable("refresh");
			}
		});
	});

	//添加按钮点击事件
	$("#btn_save").click(function() {
		$("#saveModal").modal('show');
		//清楚输入框信息
		$("#savesubject").val("");
		$("#savegrade").val("");
		$("#saveoptionA").val("");
		$("#saveoptionB").val("");
		$("#saveoptionC").val("");
		$("#saveoptionD").val("");
		$("#saveanswer").val("");
		$("#saveother_answer").val("");
		//初始化科目信息
		$("#savesubjectname option:gt(0)").remove();
		$.ajax({
			url : "queryAllSubject",
			method : 'post',
			datatype:'json',
			data : {
				
			},
			success : function(data) {
				
				$.each(data.subjectList, function(index, subjectList) {
					
		            $("#savesubjectname").append(  //此处向select中循环绑定数据
		    				"<option value="+subjectList.id+">" + subjectList.name+ "</option>");
		        });
			}
		});
	});
	
	//添加用户模态框下的确认按钮点击事件
	$("#saveConfirmBtn").click(function() {
		$("#saveModal").modal('hide');
		var type_id = $("#saveselecttype option:selected").val();
		if(type_id == -1){
			alert("请输入正确的题型");
			return false;
		}else if(type_id == 1){
			var answer = $("#saveanswer").val();
			console.log(answer);
		}else{
			var answer = $("#saveother_answer").val();
			console.log(answer);
		}
		$.ajax({
			url : "saveQuestion",
			method : 'post',
			datatype:'json',
			data : {
				subject : $("#savesubject").val(),
				optionA : $("#saveoptionA").val(),
				optionB : $("#saveoptionB").val(),
				optionC : $("#saveoptionC").val(),
				optionD : $("#saveoptionD").val(),
				point_id : $("#savepoint").val(),
				answer : answer,
				questionType_id : type_id			
			},
			success : function() {
				alert("添加成功！");
				//重新加载表格
				$("#table").bootstrapTable("refresh");
			}
		});
	});

	$('#saveselecttype').change(function() { 
			if($(this).children('option:selected').val() == -1){
				$("#savedananswer").hide();
				$("#saveotheranswer").hide();
			}else if($(this).children('option:selected').val() == 1){
				$("#savedananswer").show();
				$("#savedan").show();
				$("#saveotheranswer").hide();
			}else if($(this).children('option:selected').val() == 2){
				$("#savedananswer").show();
				$("#savedan").hide();
				$("#saveotheranswer").show();
			}else{
				$("#savedananswer").hide();
				$("#saveotheranswer").show();
			}

	});
	$('#selecttype').change(function() { 
		if($(this).children('option:selected').val() == -1){
			$("#dananswer").hide();
			$("#otheranswer").hide();
		}else if($(this).children('option:selected').val() == 1){
			$("#dananswer").show();
			$("#savedan").show();
			$("#otheranswer").hide();
		}else if($(this).children('option:selected').val() == 2){
			$("#dananswer").show();
			$("#dan").hide();
			$("#otheranswer").show();
		}else{
			$("#dananswer").hide();
			$("#otheranswer").show();
		}

});	  
	//知识点后台获取
	$('#savesubjectname').change(function() { 
		$("#savepoint option:gt(0)").remove();
		var id = $(this).children('option:selected').val();
		$.ajax({
			url : "querySBJKonwledgePoint",
			method : 'post',
			datatype:'json',
			data : {
				subject_id : id	
			},
			success : function(data) {
				$.each(data.pointList, function(index, pointList) {	
		            $("#savepoint").append(  //此处向select中循环绑定数据
		    				"<option value="+pointList.id+">" + pointList.pointName+ "</option>");
		        });
			}
		});
		
	});

	//搜索按钮点击事件
	$("#searchBtn").click(function(){

		$("#table").bootstrapTable("refresh");
		//清空搜索内容
// 		$("#selectForm").val('');
// 		$("#searchText").val('');
	});
	
	$("#table")
			.bootstrapTable(
					{
						url : "getQuestionBankInfo",
						clickToSelect : true,
						dataType : "json",
						pageSize : 10,
						pageList : [ 5, 10, 20 ],
						contentType : "application/x-www-form-urlencoded;charset=utf-8",
						method : 'get',
						dataField : "data",
						
						//是否显示详细视图和列表视图的切换按钮
						queryParams : function(params) {//自定义参数，这里的参数是传给后台的，我这是是分页用的  
							return {//这里的params是table提供的  
								offset : params.offset,//从数据库第几条记录开始  
								limit : params.limit,//找多少条
								searchType : $("#selectForm").val(),								
								searchText :$("#searchText").val()
							};
						},
						responseHandler : function(res) {
							//在ajax获取到数据，渲染表格之前，修改数据源
							return res;
						},
						columns : [
				
								{
									field : 'state',
								},
								{
									field : 'id',
									title : 'ID',
									align : 'center'
									
								},
								{
									
									width: 600,
									field : 'subject',
									title : '题目',
									align : 'left',
									formatter:function( value,row,index ){
										if(row.questionType.questionType_id == 1 || row.questionType.questionType_id == 2){
											 //如何使用拿到的多个数据 直接返回拼接好的html;
							                var html = "<span style = 'margin-left:20px' >"+row["subject"]+"</span><br><span style = 'margin-left:20px'> 选项A:"+row["optionA"]+
							                "</span><span style = 'margin-left:20px'> 选项B:"+row["optionB"]+"</span><span style = 'margin-left:20px'> 选项C:"+row["optionC"]+
							                "</span><span style = 'margin-left:20px'> 选项D:"+row["optionD"]+"</span>"
							                return html;
										}else{ return row.subject; }
						               
						            }

								},
								{
									field : 'answer',
									title : '答案',
									align : 'center'
								},
								{
									field : 'questionType.questionType_name',
									title : '问题类型',
									align : 'center'
								},
								{
									field : 'sBJKnowledgePoint.pointName',
									title : '知识点',
									align : 'center'
								},
								{
									field : 'sBJKnowledgePoint.subject.name',
									title : '科目',
									align : 'center'
								},
								{
									field : 'joinTime',
									title : '加入时间',
									align : 'center'
								},
								{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										obj = JSON.stringify(row);
										var b = "<button type='button' class='btn btn-default' onclick='edit( "
											+ obj +" )'>编辑 </button> "	;
										var d = '<button type="button" class="btn btn-danger" onclick="del(\''
												+ row.id + '\')">删除</button> ';
										return b + d ;
									}

								} ]
					});
</script>
</html>

