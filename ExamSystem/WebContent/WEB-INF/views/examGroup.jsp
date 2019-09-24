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
			<h3 class="panel-title text-center">考试一览</h3>
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
					<h4 class="modal-title" id="myModalLabel">修改考试信息</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>考试名称</label> <input type="text" class="form-control"
								id="exam_name">
						</div>
						<div class="form-group">
							<label>考试时长</label> <input type="text" class="form-control"
								id="exam_date">
						</div>
						<div class="form-group">
							<label>考试科目</label>
								<select  class="form-control" id="exam_subject">
								  <option value="-1">请选择...</option>
								</select> 
						</div>
						<div class="form-group">
							<label>考试A卷</label> 
							<select  class="form-control" id="exam_A">
								  <option value="-1">请选择...</option>
								</select> 
								<label>考试B卷</label> 
							<select  class="form-control" id="exam_B">
								  <option value="-1">请选择...</option>
								</select> 
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
	<!-- /.modal -->
	<!-- 添加用户模态框（Modal） -->
	<div class="modal fade" id="saveModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">添加考试</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>考试名称</label> <input type="text" class="form-control"
								id="saveexam_name">
						</div>
						<div class="form-group">
							<label>考试时长</label> <input type="text" class="form-control"
								id="saveexam_date">
						</div>
						<div class="form-group">
							<label>考试科目</label>
								<select  class="form-control" id="saveexam_subject">
								  <option value="-1">请选择...</option>
								</select> 
						</div>
						<div class="form-group">
							<label>考试A卷</label> 
							<select  class="form-control" id="saveexam_A">
								  <option value="-1">请选择...</option>
								</select> 
								<label>考试B卷</label> 
							<select  class="form-control" id="saveexam_B">
								  <option value="-1">请选择...</option>
								</select> 
						</div>
					
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="saveConfirmBtn">提交更改</button>
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
		$("#updateModal").modal('hide');
		$("#saveModel").modal('hide');
	});


	function del(id) {
		if (confirm("是否删除?")) {
			$.ajax({
				url : "deleteExamGroupById",
				data : {
					"examGroup_id" : id
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
	var arr;
	var A_id ;
	var B_id;
	var sub_name;
	function edit(row) {
		console.log(row);
		$("#updateModal").modal('show');
		idGlobal = row.id;
		//清楚输入框信息
		$("#exam_name").val(row.name);
		$("#exam_subject").val(row.subject_name);
		$("#exam_date").val(row.examDate);
		$("#exam_A option:gt(0)").remove();
		$("#exam_B option:gt(0)").remove();
		$("#exam_subject option:gt(0)").remove();
		 A_id = row .exam_A_id;
		 B_id = row .exam_B_id;
		 sub_name =  row.subject_name;
		//初始化用户信息
		$.ajax({
			url : 'queryAllSubjectAndExam',
			data : {
				
			},
			success : function(data){			
				$.each(data.subjectList, function(index, subjectList) {
		            $("#exam_subject").append(  //此处向select中循环绑定数据
		    				"<option value="+subjectList.id+">" + subjectList.name+ "</option>");
		        });
				$("#exam_subject option[value= -1]").attr("selected","selected"); 
				$.each(data.examList, function(index, examList) {
		            $("#exam_A").append(  //此处向select中循环绑定数据
		    				"<option value="+examList.id+">" + examList.examName+ "</option>");
		            $("#exam_B").append(  //此处向select中循环绑定数据
		    				"<option value="+examList.id+">" + examList.examName+ "</option>");
		        });
				
			//	$("#exam_subject").find("option[text="+sub_name+"]").attr("selected",true);
			//	$("#exam_subject").text(sub_name);
				$("#exam_A").val(A_id);
				$("#exam_B").val(B_id);
				
				 $('#exam_subject option:contains(' + sub_name + ')').each(function(){
					  if ($(this).text() == sub_name) {
					     $(this).attr('selected', true);
					  }
					}); 
			}
		});		
	}
	
	//编辑模态框下确认按钮的点击事件
	$("#updateConfirmBtn").click(function() {
		$("#updateModal").modal('hide');
		$.ajax({
			url : "updateExamGroupById",
			method : 'post',
			//contentType:'application/json;charset=utf-8',
			data : {
				id : idGlobal,
				name : $("#exam_name").val(),
				exam_A_id:$("#exam_A option:selected").val(),
				exam_B_id:$("#exam_B option:selected").val(),
				subject_name: $("#exam_subject option:selected").text(),
				examDate: $("#exam_date").val(),
			},
			success : function() {
				alert("修改成功！");
				//重新加载表格
				$("#table").bootstrapTable("refresh");
			}
		});
	});

	//添加按钮点击事件
	$("#btn_save").click(function() {
		$("#saveModal").modal('show');
		//清楚输入框信息
		$("#saveexam_name").val("");
		$("#saveexam_date").val("");
		$("#saveexam_subject option:gt(0)").remove();
		$("#saveexam_A option:gt(0)").remove();
		$("#saveexam_B option:gt(0)").remove();
		//初始化学院信息
		$.ajax({
			url : 'queryAllSubjectAndExam',
			data : {
				
			},
			success : function(data) {			
				$.each(data.subjectList, function(index, subjectList) {
		            $("#saveexam_subject").append(  //此处向select中循环绑定数据
		    				"<option value="+subjectList.id+">" + subjectList.name+ "</option>");
		        });
				$("#saveexam_subject option[value= -1]").attr("selected","selected"); 
				$.each(data.examList, function(index, examList) {
		            $("#saveexam_A").append(  //此处向select中循环绑定数据
		    				"<option value="+examList.id+">" + examList.examName+ "</option>");
		            $("#saveexam_B").append(  //此处向select中循环绑定数据
		    				"<option value="+examList.id+">" + examList.examName+ "</option>");
		        });
				$("#saveexam_A option[value= -1]").attr("selected","selected"); 
				$("#saveexam_B option[value= -1]").attr("selected","selected"); 
			}
		});		
	});
	
	//添加用户模态框下的确认按钮点击事件
	$("#saveConfirmBtn").click(function() {
		$("#saveModal").modal('hide');
		console.log(sub_name);
		$.ajax({
			url : "saveExamGroup",
			method : 'post',
			datatype:'json',
			data : {
			//	subject_name: $(".saveexam_subject").find("option:selected").text(),
				subject_name :$("#saveexam_subject option:selected").text(),
				name :$("#saveexam_name").val(),
				examDate: $("#saveexam_date").val(),
				exam_A_id:$("#exam_A option:selected").val(),
				exam_B_id:$("#exam_B option:selected").val()
			},
			success : function() {
				alert("添加成功！");
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
						url : "getExamGroupForTeacherInfo",
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
									field : 'name',
									title : '考试名称',
									align : 'center'
								},
								{
									field : 'teacher_name',
									title : '老师',
									align : 'center'
								},
								{
									field : 'subject_name',
									title : '科目',
									align : 'center'
								},
								{
									field : 'exam_A_id',
									title : '试卷预览',
									align : 'center',
									formatter : function(value, row, index) {
											var e = '<button type="button" class="btn btn-default" onclick="watch(\''
													+ row.exam_A_id + '\')">A卷预览 </button> ';
											var d = '<button type="button" class="btn btn-default" onclick="watch(\''
													+ row.exam_B_id + '\')">B卷预览 </button> ';
													
											return e + d ;
										}
								},								
								{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										
										obj = JSON.stringify(row);
										var c = "<button type='button' class='btn btn-default' onclick='edit( "
												+ obj +" )'>编辑 </button> "	;
										
										var d = '<button type="button" class="btn btn-danger" onclick="del(\''
												+ row.id + '\')">删除</button> ';
										return c + d ;
									}

								} ]
					});
</script>
</html>

