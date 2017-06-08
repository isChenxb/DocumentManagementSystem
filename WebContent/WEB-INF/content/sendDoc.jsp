<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic Form - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/demo.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
</head>
<body>
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="公文登记" style="width:100%;max-width:400px;padding:30px 60px;">
		<form id="ff" method="post" action="logDocAction" enctype="multipart/form-data">
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="doc_name" style="width:100%" data-options="label:'公文名称：',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="from_user" style="width:100%" data-options="label:'拟稿人：',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-datetimebox" name="start_time" data-options="label:'拟稿时间',required:true,showSeconds:false" style="width:230px">
			</div>
			<div style="margin-bottom:20px">
			<input id="cc1" class="easyui-combobox" name="target_dep" data-options="required:true , label:'拟办部门' , valueField: 'dep_id', textField: 'dep_name', 
			                                url: 'depNames',
			                                onSelect: function(rec){
                                                var url = 'userNames?dep_id='+rec.dep_id;
                                                $('#cc2').combobox('reload', url)
                                                $('#cc2').combobox('clear');}">
			</div>
			<div style="margin-bottom:20px">
                <input id="cc2" class="easyui-combobox" name="target_user" data-options="required:true , label:'拟办人' , valueField:'username',textField:'name'">
			</div>
			<div style="margin-bottom:20px">
                <input class="easyui-combobox" name="emergency" 
                       data-options="label:'紧急程度',
                                     url: 'emergency.json', 
                                     valueField:'emergency',
                                     textField:'emergency',
                                     required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="doc_des" style="width:100%;height:60px" data-options="label:'公文摘要:',multiline:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="others" style="width:100%;height:60px" data-options="label:'备注:',multiline:true">
			</div>
			<div style="margin-bottom:20px">
			    <input class="easyui-filebox" name="upload" id="source_sign" style="width:100%" data-options="buttonText:'选择文件' , required:true">
			</div>
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">重置</a>
		</div>
	</div>
	<script>
		function submitForm(){
			$('#ff').form('submit', {
				success: function(result){
					$.messager.alert('登记结果',result,'info',clearForm);
				}
			});
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
</body>
</html>