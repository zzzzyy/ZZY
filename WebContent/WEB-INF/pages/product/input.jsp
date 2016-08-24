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
	    		$("#productForm").submit();
	    		return false;
	    	});
	    })
    </script>
    
  </head>
 <body class="main">
 	<span class="page_title">新建/修改销售机会</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
		<button class="common_button" id="save">保存</button>
	</div>
	
	<form:form id="productForm" action="${ctp}/product/${product.id }" method="POST" modelAttribute="product">
		<c:if test="${product.id != null }">
			<input type="hidden" name="_method" value="PUT"/>
		</c:if>
		
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>名称</th>
				<td>
					<form:input path="name"/>
				</td>
				<th>型号</th>
				<td><form:input path="type"/></td>
			</tr>
			<tr>
				<th>等级/批次</th>
				<td><form:input path="batch"/><span class="red_star">*</span></td>
				<th>单位</th>
				<td><form:input path="unit"/><span class="red_star">*</span></td>
			</tr>
			<tr>
				<th>单价</th>
				<td><form:input path="price"/><span class="red_star">*</span></td>
				<th>备注</th>
				<td><form:input path="memo"/><span class="red_star">*</span></td>
			</tr>
			
			
		</table>
		<br /><br>
  	</form:form>
  </body>
</html>
