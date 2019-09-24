<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 引入bootstrap样式 -->
	<link href="../css/bootstrap.min.css" rel="stylesheet">
	<!-- 引入bootstrap-table样式 -->
	<link href="../css/bootstrap-table.min.css" rel="stylesheet">
	
	<link rel="stylesheet"href="../css/blue.css" />
	
	<!-- jquery -->
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/icheck.js"></script>
	
	<!-- bootstrap-table.min.js -->
	<script src="../js/bootstrap-table.min.js"></script>
	<!-- 引入中文语言包 -->
	<script src="../js/bootstrap-table-zh-CN.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title text-center">成绩查询</h3>
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

	
</body>


<script type="text/javascript">

	$(document).ready(function() {
		$("button[name='toggle']").height(20);
		$("button[name='refresh']").height(20);
		
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
						url : "getStudentExamForStudentInfo",
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
									align : 'center',
									formatter : function(value, row, index) {
										
								if(row.score != row.singleScore && row.moreScore == 0 ){
									var c = "未批改";
								}else{
									var c = row.score
								}
									
								return c  ;
								}

								 
								},
								{
									field : 'examDate',
									title : '考试时间',
									align : 'center'
								},
								/* {
									title : '操作',
									field : 'school_id',
									align : 'center',
									formatter : function(value, row, index) {
										
												obj = JSON.stringify(row);
										var c = "<button type='button' class='btn btn-default' onclick='edit( "
													+ obj +" )'>编辑 </button> "	;
										var d = '<button type="button" class="btn btn-danger" onclick="del(\''
												+ row.school_id + '\')">删除</button> ';
										return c + d ;
									}

								} */ ]
					});
</script>
</html>

