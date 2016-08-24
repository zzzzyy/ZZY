<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>新建销售机会</title>
    <script type="text/javascript">
	    $(function(){
	    	$("#save").click(function(){
	    		$("#storageForm").submit();
	    		return false;
	    	});
	    })
    </script>
    
  </head>
    <title>增加用户</title>
  </head>

  <body class="main">
  
  	<form:form id="storageForm" action="${ctp }/user/${user.id }" method="post" modelAttribute="user">
  	
		<c:if test="${user.id != null }">
		<input type="hidden" name="_method" value="PUT"/>
	  		<span class="page_title">用户管理　&gt;　修改用户</span>
			<form:hidden path="id"/>
		</c:if>
		<c:if test="${user.id == null }">
	  		<span class="page_title">用户管理　&gt;　新建用户</span>
		</c:if>
		
		<div class="button_bar">
			<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
			<button class="common_button" id="save">保存</button>
		</div>
		
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th class="input_title">用户名</th>
				<td class="input_content">
					<form:input path="name"/>
					<div id='divCheck'></div>
				</td>
				
				<th class="input_title">密码</th>
				<td class="input_content">
					<form:password path="password"/>
				</td>
			</tr>
			<tr>
				<th class="input_title">角色</th>
				<td class="input_content">
					<form:select path="role.id" items="${list}"
						itemValue="id" itemLabel="name"></form:select>
				</td>
				<th class="input_title">状态</th>
				<td class="input_content">
					 <form:radiobuttons path="enabled" items="${allStatus }"/> 
				</td>
			</tr>
		</table>
	</form:form>
	
  </body>
</html>
