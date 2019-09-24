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
			<h3 class="panel-title text-center">学生管理</h3>
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
					<h4 class="modal-title" id="myModalLabel">修改学生信息</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>姓名</label> <input type="text" class="form-control"
								id="student_name">
						</div>
						<div class="form-group">
							<label>学号</label> <input type="text" class="form-control"
								id="student_number">
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">班级:</label>
						      <div class="form-inline">
							    
								<select class="form-control"   id="class_id">
								   <option value="-1">请选择...</option>
								</select>
								
							 </div>
						</div>
						<div class="form-group">
							<label>登录账号</label> <input type="text" class="form-control"
								id="user_name">
						</div>
						<div class="form-group">
							<label>登录密码</label> <input type="text" class="form-control"
								id="user_password">
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
					<h4 class="modal-title" id="myModalLabel">添加学生</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label>姓名</label> <input type="text" class="form-control"
								id="savestudent_name">
						</div>
						<div class="form-group">
							<label>学号</label> <input type="text" class="form-control"
								id="savestudent_number">
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">班级:</label>
						      <div class="form-inline">
							    
								<select class="form-control"   id="saveclass_id">
								   <option value="-1">请选择...</option>
								</select>
								
							 </div>
						</div>
						<div class="form-group">
							<label>登录账号</label> <input type="text" class="form-control"
								id="saveuser_name">
						</div>
						<div class="form-group">
							<label>登录密码</label> <input type="text" class="form-control"
								id="saveuser_password">
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
		$("#FileModal").modal('hide');
	});


	function del(id) {
		if (confirm("是否删除?")) {
			$.ajax({
				url : "deleteStudentById",
				data : {
					"student_id" : id
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
	function edit(id) {
		$("#updateModal").modal('show');
		idGlobal = id;
		//清楚输入框信息
		$("#student_number").val();
		$("#student_name").val();
		$("#user_number").val();
		$("#user_password").val();
		$("#class_id option:gt(0)").remove();
		//初始化用户信息
		$.ajax({
			url : 'queryStudentById',
			data : {
				'student_id' : idGlobal
			},
			success : function(data) {
				
				$.each(data.classList, function(index, classList) {
		            $("#class_id").append(  //此处向select中循环绑定数据
		    				"<option value="+classList.class_id+">" + classList.class_name+ "</option>");
		        });
				$("#class_id option[value='"+data.student.class_id.class_id+"']").attr("selected","selected"); 
				$("#student_number").val(data.student.number);
				$("#student_name").val(data.student.name);
				$("#user_name").val(data.student.user_id.user_name);
				$("#user_password").val(data.student.user_id.user_pwd);
			}
		});		
	}
	
	//编辑模态框下确认按钮的点击事件
	$("#updateConfirmBtn").click(function() {
		$("#updateModal").modal('hide');
		$.ajax({
			url : "updateStudentById",
			method : 'post',
			//contentType:'application/json;charset=utf-8',
			data : {
				stu_id : idGlobal,
				name : $("#student_name").val(),
				number : $("#student_number").val(),
				user_name : $("#user_name").val(),
				user_pwd :$("#user_password").val(),
				classid : $("#class_id option:selected").val()
				
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
		$("#savestudent_number").val("");
		$("#savestudent_name").val("");
		$("#saveuser_name").val("");
		$("#saveuser_password").val("");
		$("#saveclass_id option:gt(0)").remove();
		//初始化信息
		$.ajax({
			url : 'queryStudentById',
			data : {
				'student_id' : 1
			},
			success : function(data) {			
				$.each(data.classList, function(index, classList) {
		            $("#saveclass_id").append(  //此处向select中循环绑定数据
		    				"<option value="+classList.class_id+">" + classList.class_name+ "</option>");
		        });
				$("#saveclass_id option[value= -1]").attr("selected","selected"); 
			}
		});		
	});
	
	//添加用户模态框下的确认按钮点击事件
	$("#saveConfirmBtn").click(function() {
		$("#saveModal").modal('hide');
		$.ajax({
			url : "saveStudent",
			method : 'post',
			datatype:'json',
			data : {
				name : $("#savestudent_name").val(),
				number : $("#savestudent_number").val(),
				user_name : $("#saveuser_name").val(),
				user_number :$("#saveuser_password").val(),
				classid : $("#saveclass_id option:selected").val()
				
		
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
						url : "getStudentInfo",
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
									field : 'stu_id',
									title : 'ID',
									align : 'center'
								},
								{
									field : 'name',
									title : '姓名',
									align : 'center'
								},
								{
									field : 'number',
									title : '学号',
									align : 'center'
								},
								{
									field : 'class_id.class_name',
									title : '班级',
									align : 'center'
								},
								{
									field : 'class_id.college_id.college_name',
									title : '学院',
									align : 'center'
								},
								{
									field : 'class_id.college_id.school_id.school_name',
									title : '学校',
									align : 'center'
								},
								{
									title : '操作',
									field : 'stu_id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<button type="button" class="btn btn-default" onclick="edit(\''
												+ row.stu_id + '\')">编辑</button> ';
										var d = '<button type="button" class="btn btn-danger" onclick="del(\''
												+ row.stu_id + '\')">删除</button> ';
										return e + d ;
									}

								} ]
					});
</script>
</html>

