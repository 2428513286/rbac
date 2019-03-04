<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<style type="text/css">
	body{
		text-align: center;
	}
	
	#div{
		text-align: center;
		margin-top: 280px;
	}
	h1{
		margin-top: 80px;
		
	}
</style>
<script type="text/javascript">
	

</script>
</head>
<body>
	<h1>欢迎</h1>
	<div id="div">
		帐号：<input class="easyui-textbox" data-options="iconCls:'icon-man'" ><br>
		<div style="height: 20px;"></div>
		密码：<input class="easyui-passwordbox" data-options="iconCls:'icon-lock'" ><br>
		<div style="height: 45px;"></div>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">注册</a>  
	</div>
</body>
</html>