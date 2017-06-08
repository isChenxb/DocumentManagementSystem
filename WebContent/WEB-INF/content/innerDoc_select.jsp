<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Custom DataGrid Pager - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/demo.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
</head>
<body>
	<div style="margin:20px 0;"></div>
	<table id="dg" title="公文查询" style="width:'1000'"
			data-options="
			rownumbers:true,
			singleSelect:true,
			pagination:true,
			multiSort: true,
			remoteSort: false,
			method:'get'">
		<thead>
			<tr>
				<th data-options="field:'doc_id',width:120 ,sortable:true">公文编号</th>
				<th data-options="field:'doc_type',width:80 , sortable:true">公文类型</th>
				<th data-options="field:'doc_name',width:120,align:'right'">公文名称</th>
				<th data-options="field:'emergency',width:60,align:'right' , sortable:true">紧急程度</th>
				<th data-options="field:'from_dep',width:80 , sortable:true">发文部门</th>
				<th data-options="field:'target_dep',width:80,align:'center' , sortable:true">拟办部门</th>
				<th data-options="field:'start_time',width:120,align:'center' , sortable:true">发文时间</th>
				<th data-options="field:'process_time',width:120,align:'center' , sortable:true">办理时间</th>
				<th data-options="field:'inprocess',width:80,align:'right' ,sortable:true">待办理人</th>
				<th data-options="field:'status',width:80,align:'right' , sortable:true">公文状态</th>
			    <th data-options="field:'id', width:80, formatter: operation">操作</th> 
				</tr>
		</thead>
	</table>
	
	<div id="tb">
	    <span>查询类型： </span>
        <input class="easyui-combobox" id="type" name="type" 
                       data-options="url: 'select_type.json', 
                                     valueField:'id',
                                     textField:'type',
                                     required:true">
        <span style="margin-left:10px"></span>
        <span>办理时间：</span>
		<input class="easyui-datetimebox" id="from_time" name="from_time" data-options="showSeconds:false" style="width:150px">
		<span>至</span>
		<input class="easyui-datetimebox" id="to_time" name="to_time" data-options="showSeconds:false" style="width:150px">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="select()" style="width:80px">查询</a>
	</div>
	<script type="text/javascript">
	//查看详情
    function operation(value, row, index) 
	{
		return "<a href='myInnerDocDetail?doc_id="+ row.doc_id +"' target='_self'>查看</a>"; 
	}
	
	function select() {
		var type = $('#type').combobox("getValue");
		var from_time = $('#from_time').datetimebox("getValue");
		var to_time = $('#to_time').datetimebox("getValue");
		if (type == 1) {
		   $('#dg').datagrid({
			url:'innerDocSelect_myApprove',
			queryParams: {
				from: from_time,
				to: to_time
			}
		   });
		} else if (type == 2) {
			$('#dg').datagrid({
				url:'innerDocSelect_myProcess',
				queryParams: {
					from: from_time,
					to: to_time
				}
			   });
		}
	}
	
		$(function(){
			var pager = $('#dg').datagrid().datagrid('getPager');	// get the pager of datagrid
			$('#dg').datagrid({
				toolbar: '#tb'
			});
		})
	</script>
</body>
</html>