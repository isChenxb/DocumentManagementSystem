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
	<div class="easyui-panel" title="修改密码" style="width:100%;max-width:400px;padding:30px 60px;">
		<form id="ff" method="post" action="changePassAction" enctype="multipart/form-data">
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="old_pass" style="width:100%" data-options="label:'初始密码：',required:true, type:'password'">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="new_pass1" style="width:100%" data-options="label:'新密码：',required:true, type:'password'">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="new_pass2" style="width:100%" data-options="label:'确认新密码：',required:true, type:'password'">
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
					$.messager.alert('密码修改结果',result,'info',clearForm);
				}
			});
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
</body>
</html>