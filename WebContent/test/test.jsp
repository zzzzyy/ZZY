<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    

<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="${ctp }/static/jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctp }/static/jquery/themes/icon.css">
<script type="text/javascript" src="${ctp }/static/jquery/jquery.easyui.min.js"></script>

</head>
<body>

	<% 
		request.setAttribute("testKey", "testVal");
	%>
	
	<atguigu:helloworld count="10">
		request: ${requestScope.testKey }
	</atguigu:helloworld>
	
	<br><br>
	
	<!--  
	1. 需要把 tomcat 的 conf 目录下的 web.xml 文件的 DefaultServlet 的 listings 参数修改为 true.
	2. 输入 http://localhost:8080/jquery-easyui-1.3.5/demo/tree/animation.html 地址来演示 easyui tree
	3. 导入 easyui.css、icon.css 和 jquery.easyui.min.js
	4. 复制
	<ul class="easyui-tree" data-options="url:'tree_data1.json',method:'get',animate:true"></ul>
	到当前页面
	5. 复制 tree_data1.json 到和 JSP 相同的目录. 
	-->
	<ul class="easyui-tree" data-options="url:'tree_data1.json',method:'get',animate:true"></ul>
	
	
</body>
</html>