<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/demo.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
</head>
<body>
    <div style="margin:20px 0;"></div>
	<table id="dg" title="待处理公文" style="width:'1000'"
			data-options="
			rownumbers:true,
			singleSelect:true,
			pagination:true,
			url:'getMyInnerProcess',
			method:'get'">
		<thead>
			<tr>
				<th data-options="field:'doc_id',width:120">公文编号</th>
				<th data-options="field:'doc_type',width:80">公文类型</th>
				<th data-options="field:'doc_name',width:120,align:'right'">公文名称</th>
				<th data-options="field:'emergency',width:60,align:'right'">紧急程度</th>
				<th data-options="field:'from_dep',width:80">发文部门</th>
				<th data-options="field:'target_dep',width:80,align:'center'">拟办部门</th>
				<th data-options="field:'start_time',width:120,align:'center'">发文时间</th>
				<th data-options="field:'inprocess',width:80,align:'right'">待办理人</th>
				<th data-options="field:'status',width:80,align:'right'">公文状态</th>
			    <th data-options="field:'id', width:80, formatter: operation">操作</th> 
				</tr>
		</thead>
	</table>
	<script type="text/javascript">
	//查看详情
    function operation(value, row, index) 
	{
		return "<a href='innerProcessDetail?doc_id="+ row.doc_id +"' target='_self'>查看</a>"; 
	}
	
    $(function(){
		var pager = $('#dg').datagrid().datagrid('getPager');	// get the pager of datagrid	
	})
	</script>
</body>
</html>