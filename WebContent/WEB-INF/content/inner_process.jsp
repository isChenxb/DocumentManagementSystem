<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<div class="easyui-panel" title="收文办理" style="width:600px;padding:30px 60px;">
	        <div style="margin-bottom:20px">
	            <span>基本信息</span>
	            <div class="datagrid-toolbar" style="margin-top:5px"></div>
			</div>
	        <div style="margin-bottom:20px">
			<span>
				<input value="<%=request.getAttribute("doc_id")%>" class="easyui-textbox" id="doc_id" name="doc_id" style="width:200px" data-options="label:'公文编号：',readonly:true">
			</span>
			<span style="margin-left:40px">
				<input class="easyui-textbox" name="doc_type" style="width:200px" value="<%=request.getAttribute("doc_type")%>" data-options="label:'公文类型：',readonly:true">
			</span>
			</div>
			
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="doc_name" style="width:450px" value="<%=request.getAttribute("doc_name")%>" data-options="label:'公文名称：',readonly:true">
			</div>
			
			 <div style="margin-bottom:20px">
			<span>
				<input class="easyui-textbox" name="emergency" style="width:200px" value="<%=request.getAttribute("emergency")%>" data-options="label:'紧急程度：',readonly:true">
			</span>
			<span style="margin-left:40px">
				<input class="easyui-textbox" name="inprocess" style="width:200px" value="<%=request.getAttribute("inprocess")%>" data-options="label:'待处理人：',readonly:true">
			</span>
			</div>
			
			<div style="margin-bottom:20px">
			<span>
				<input class="easyui-textbox" name="target_dep" style="width:200px" value="<%=request.getAttribute("target_dep")%>" data-options="label:'拟办部门：',readonly:true">
			</span>
			<span style="margin-left:40px">
				<input class="easyui-textbox" name="target_user" style="width:200px" value="<%=request.getAttribute("target_user")%>" data-options="label:'拟办人：',readonly:true">
			</span>
			</div>
			
			<div style="margin-bottom:20px">
			<span>
				<input class="easyui-textbox" name="doc_des" style="width:450px;height:100px" value="<%=request.getAttribute("doc_des")%>" data-options="label:'公文摘要：',multiline:true,readonly:true">
			</span>
			</div>
			
			<div style="margin-bottom:20px">
			<span>
				<input class="easyui-textbox" name="others" style="width:450px;height:60px" value="<%=request.getAttribute("other")%>" data-options="label:'备注：',multiline:true,readonly:true">
			</span>
			</div>
			
			<div style="margin-bottom:20px;margin-top:20px">
	            <span>发文信息</span>
	            <div class="datagrid-toolbar" style="margin-top:5px"></div>
			</div>
			
			<div style="margin-bottom:20px">
			<span>
				<input class="easyui-textbox" name="from_dep" style="width:200px" value="<%=request.getAttribute("from_dep")%>" data-options="label:'发文部门：',readonly:true">
			</span>
			<span style="margin-left:40px">
				<input class="easyui-textbox" name="from_user" style="width:200px" value="<%=request.getAttribute("from_uesr")%>" data-options="label:'拟稿人：',readonly:true">
			</span>
			</div>
			
			<div style="margin-bottom:20px">
			<span>
				<input class="easyui-textbox" name="start_time" style="width:300px" value="<%=request.getAttribute("start_time")%>" data-options="label:'拟稿时间：',readonly:true">
			</span>
			</div>
			
			<div style="margin-bottom:20px">
			<span>
				<input class="easyui-textbox" name="log_user" style="width:200px" value="<%=request.getAttribute("log_user")%>" data-options="label:'登记人：',readonly:true">
			</span>
			<span style="margin-left:40px">
				<input class="easyui-textbox" name="log_time" style="width:200px" value="<%=request.getAttribute("log_time")%>" data-options="label:'登记时间：',readonly:true">
			</span>
			</div>
			
			<div style="margin-bottom:20px;margin-top:20px">
	            <span>公文状态</span>
	            <div class="datagrid-toolbar" style="margin-top:5px"></div>
			</div>
			
			<div style="margin-bottom:20px">
			<span>
				<input class="easyui-textbox" name="approve_log" style="width:450px;height:60px" value="<%=request.getAttribute("approve_log")%>" data-options="label:'审核状态：',multiline:true,readonly:true">
			</span>
			</div>
			
			<div style="margin-bottom:20px">
			<span>
				<input class="easyui-textbox" name="approve_log" style="width:450px;height:60px" value="<%=request.getAttribute("process_log")%>" data-options="label:'办理状态：',multiline:true,readonly:true">
			</span>
			</div>
			
			<div style="margin-bottom:20px;margin-top:20px">
	            <span>收文办理</span>
	            <div class="datagrid-toolbar" style="margin-top:5px"></div>
			</div>
			
			<div style="text-align:center;padding:5px 0">
			<a href="download?doc_id=<%=request.getAttribute("doc_id")%>&fileName=<%=java.net.URLDecoder.decode((String)request.getAttribute("fileName"),"utf-8")%>" target="_blank" class="easyui-linkbutton" style="width:80px">文件下载</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doProcess()" style="width:80px">公文承办</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="entrustProcess()" style="width:80px">公文委办</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doNotProcess()" style="width:80px">公文退办</a>
		    </div>
	</div>
	
	<div id="doProcess_dialog" class="easyui-dialog" title="公文承办" style="width:550px;height:400px;padding:10px"
			data-options="
				iconCls: 'icon-save',
				buttons: [{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){
					     $('#doProcess_form').form('submit', {
				             success: function(result){
					           $.messager.alert('提交结果',result,'info',back);
				             }
			             });
					}
				   },{
					text:'取消',
					handler:function(){
						$('#doProcess_dialog').dialog('close');
					}
				}],
				closed: true,
				modal:true
			">
			<div style="margin-bottom:20px;margin-top:20px">
	            <span>提交承办意见</span>
	            <div class="datagrid-toolbar" style="margin-top:5px"></div>
			</div>
			<form id="doProcess_form" method="post" action="innerProcess_doProcess" enctype="multipart/form-data">
			<div style="margin-bottom:20px">
				<input value="<%=request.getAttribute("doc_id")%>" class="easyui-textbox" name="doc_id" style="width:200px" data-options="label:'公文编号：',readonly:true">
            </div>
            
            <div style="margin-bottom:20px">
				<input value="公文承办" class="easyui-textbox" name="process_type" style="width:200px" data-options="label:'办理类型：',readonly:true">
            </div>
            
			<div style="margin-bottom:20px">
			<span>
				<input class="easyui-textbox" name="process_message" style="width:450px;height:100px" data-options="label:'办理意见：',multiline:true,required:true">
			</span>
			</div>
			</form>
	</div>
	
	<div id="doNotProcess_dialog" class="easyui-dialog" title="公文退办" style="width:550px;height:400px;padding:10px"
			data-options="
				iconCls: 'icon-save',
				buttons: [{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){
					     $('#doNotProcess_form').form('submit', {
				             success: function(result){
					           $.messager.alert('提交结果',result,'info',back);
				             }
			             });
					}
				   },{
					text:'取消',
					handler:function(){
						$('#doNotProcess_dialog').dialog('close');
					}
				}],
				closed: true,
				modal:true
			">
			<div style="margin-bottom:20px;margin-top:20px">
	            <span>提交退办意见</span>
	            <div class="datagrid-toolbar" style="margin-top:5px"></div>
			</div>
			<form id="doNotProcess_form" method="post" action="innerProcess_doNotProcess" enctype="multipart/form-data">
			<div style="margin-bottom:20px">
				<input value="<%=request.getAttribute("doc_id")%>" class="easyui-textbox" name="doc_id" style="width:200px" data-options="label:'公文编号：',readonly:true">
            </div>
            
            <div style="margin-bottom:20px">
				<input value="公文退办" class="easyui-textbox" name="process_type" style="width:200px" data-options="label:'办理类型：',readonly:true">
            </div>
            
			<div style="margin-bottom:20px">
			<span>
				<input class="easyui-textbox" name="process_message" style="width:450px;height:100px" data-options="label:'退办意见：',multiline:true,required:true">
			</span>
			</div>
			</form>
	</div>
	
	<div id="entrustProcess_dialog" class="easyui-dialog" title="公文委办" style="width:550px;height:470px;padding:10px"
			data-options="
				iconCls: 'icon-save',
				buttons: [{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){
					     $('#entrustProcess_form').form('submit', {
				             success: function(result){
					           $.messager.alert('提交结果',result,'info',back);
				             }
			             });
					}
				   },{
					text:'取消',
					handler:function(){
						$('#entrustProcess_dialog').dialog('close');
					}
				}],
				closed: true,
				modal:true
			">
			<div style="margin-bottom:20px;margin-top:20px">
	            <span>提交委办意见</span>
	            <div class="datagrid-toolbar" style="margin-top:5px"></div>
			</div>
			<form id="entrustProcess_form" method="post" action="innerProcess_entrustProcess" enctype="multipart/form-data">
			<div style="margin-bottom:20px">
				<input value="<%=request.getAttribute("doc_id")%>" class="easyui-textbox" name="doc_id" style="width:200px" data-options="label:'公文编号：',readonly:true">
            </div>
            
            <div style="margin-bottom:20px">
				<input value="公文委办" class="easyui-textbox" name="process_type" style="width:200px" data-options="label:'办理类型：',readonly:true">
            </div>
            
            <div style="margin-bottom:20px">
			<input id="cc1" class="easyui-combobox" name="target_dep" data-options="required:true , label:'委办部门：' , valueField: 'dep_id', textField: 'dep_name', 
			                                url: 'depNames',
			                                onSelect: function(rec){
                                                var url = 'userNames?dep_id='+rec.dep_id;
                                                $('#cc2').combobox('reload', url)
                                                $('#cc2').combobox('clear');}">
			</div>
			<div style="margin-bottom:20px">
                <input id="cc2" class="easyui-combobox" name="entrust_user" data-options="required:true , label:'委办人：' , valueField:'username',textField:'name'">
			</div>
            
			<div style="margin-bottom:20px">
			<span>
				<input class="easyui-textbox" name="process_message" style="width:450px;height:100px" data-options="label:'委办意见：',multiline:true,required:true">
			</span>
			</div>
			</form>
	</div>
	
	
	<script type="text/javascript">
	     function back() {
	    	 window.open('myInnerProcess','_self');
	     }
	
	     function doProcess() {
	    	 $('#doProcess_dialog').dialog('open')
	     }
	     
	     function entrustProcess() {
	    	 $('#entrustProcess_dialog').dialog('open')
	     }
	     
	     function doNotProcess() {
	    	 $('#doNotProcess_dialog').dialog('open')
	     }
	</script>
</body>
</html>