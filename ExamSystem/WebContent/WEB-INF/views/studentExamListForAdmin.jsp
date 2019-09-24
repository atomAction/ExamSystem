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
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title text-center">批改</h3>
		</div>
		<div class="panel-body">

			<div id="toolbar" class="btn-group">
				<div class="form-inline">
					<select class="form-control" id="selectForm"  >
						<option value="id">ID</option>
						<option value="name">名称</option>
					</select>
					<input class="form-control" id="searchText"  type="text" placeholder="请输入搜索内容"></input>
					<button class="btn btn-info" id="searchBtn" >搜索</button>					
				</div>
			</div>

			<table data-toggle="table" id="table" data-height="650"
				data-classes="table table-hover" data-striped="true"
				data-pagination="true" data-side-pagination="server"
				data-show-refresh="true" data-show-toggle="true"
				data-show-columns="true" data-toolbar="#toolbar">
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
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">批改</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>学生姓名</label> <input type="text" class="form-control"
								id="student_name">
						</div>
						<div class="form-group">
						    <label for="answer" class="col-sm-2 control-label">参考答案：</label>
						    <div class="col-sm-10">
						      <textarea class="form-control" rows="5" id="answer"></textarea>
						    </div>
		 				</div>
						 <div class="form-group">
						    <label for="studentanswer" class="col-sm-2 control-label">学生作答：</label>
						    <div class="col-sm-10">
						      <textarea class="form-control" rows="3" id="studentanswer"></textarea>
						    </div>
		 				</div>
		 				<div class="form-group">
							<label>输入分值（满分10分）</label> 
							<input class="form-control" id="score" 
								onkeyup="if(isNaN(value))execCommand('undo')" 
								onafterpaste="if(isNaN(value))execCommand('undo')">
						</div>
		 				<div class="form-group">
						    <label for="answer1" class="col-sm-2 control-label">参考答案：</label>
						    <div class="col-sm-10">
						      <textarea  class="form-control" rows="5" id="answer1"></textarea>
						    </div>
		 				</div>
						 <div class="form-group">
						    <label for="studentanswer1" class="col-sm-2 control-label">学生作答：</label>
						    <div class="col-sm-10">
						      <textarea class="form-control" rows="3" id="studentanswer1"></textarea>
						    </div>
		 				</div>
						<div class="form-group">
							<label>输入分值（满分10分）</label> 
								<input class="form-control" id="score1" 
								onkeyup="if(isNaN(value))execCommand('undo')" 
								onafterpaste="if(isNaN(value))execCommand('undo')">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="updateConfirmBtn">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
</body>


<script type="text/javascript">

	$(document).ready(function() {
		$("button[name='toggle']").height(20);
		$("button[name='refresh']").height(20);
		$("#updateModal").modal('hide');

	});




	//按钮点击事件
	var studentExam_id ;
	function edit(obj) {
		$("#updateModal").modal('show');
		//初始化
	//	var obj1 = JSON.parse(obj);

	//	console.log(questions);
	//	console.log(questions.answer);
	//	idGlobal= obj.school_id;
		$("#student_name").val(obj.student.name);
		$("#answer").val(obj.exam.questions[20].answer);
		$("#studentanswer").val(obj.answerMap[20]);
		$("#answer1").val(obj.exam.questions[21].answer);
		$("#studentanswer1").val(obj.answerMap[21]);
		studentExam_id = obj.id;
	}
	
	//编辑模态框下确认按钮的点击事件
	$("#updateConfirmBtn").click(function() {
		$("#updateModal").modal('hide');
		if($("#score1").val() > 10 && $("#score1").val() < 0){
			alert("输入错误！请输入正确的分值");
			return false;
		}else if($("#score").val() > 10 && $("#score").val() < 0){
			alert("输入错误！请输入正确的分值");
			return false;
		}else{
			var getscore = $("#score").val() + $("#score1").val();
		}
		
		$.ajax({
			url : "updateStudentExamScore",
			method : 'post',
			contentType:'application/json;charset=utf-8',
			data : JSON.stringify({
				studentExam_id : studentExam_id,
				getscore : getscore
				
			}),
			success : function() {
				alert("修改成功！");
				//重新加载表格
				$("#table").bootstrapTable("refresh");
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
				url : "getStudentExamForAdminInfo",
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
							field : 'examGroup.name',
							title : '考试名称',
							align : 'center'
						},
						{
							field : 'singleScore',
							title : '单选得分',
							align : 'center'
						},
						{
							field : 'moreScore',
							title : '简答题得分',
							align : 'center'
						},
						{
							field : 'score',
							title : '总得分',
							align : 'center'
						},
						{
							field : 'examDate',
							title : '考试时间',
							align : 'center'
						},
						 {
							title : '操作',
							field : 'id',
							align : 'center',
							formatter : function(value, row, index) {				
										obj = JSON.stringify(row);
								var c = "<button type='button' class='btn btn-default' onclick='edit( "
											+ obj +" )'>批改 </button> "	;
								return c  ;
							}

						}  ]
			});
</script>
</html>

