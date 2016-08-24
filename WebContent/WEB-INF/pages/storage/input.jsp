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
 <body class="main">
 	<span class="page_title">新建/修改销售机会</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
		<button class="common_button" id="save">保存</button>
	</div>
	
	<form:form id="storageForm" action="${ctp}/storage/${storage.id }" method="POST" modelAttribute="storage">
		<c:if test="${storage.id != null }">
			<input type="hidden" name="_method" value="PUT"/>
		</c:if>
		
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>产品-false-</th>
				<td><c:if test="${storage.id == null }">
						<form:select path="product.id" items="${list }"
							itemLabel="name" itemValue="id"></form:select>
					</c:if> <c:if test="${storage.id != null }">
						<form:input path="product.name" readonly="true" />
						<form:hidden path="product.id" readonly="true" />
					</c:if></td>
 
				</td>
				<th>仓库</th>
				<td><form:input path="wareHouse"/></td>
			</tr>
			<tr>
				<th>货位</th>
				<td><form:input path="stockWare"/><span class="red_star">*</span></td>
				<c:if test="${empty storage.stockCount }">
				<th>数量</th>
				<td><form:input path="stockCount"/><span class="red_star">*</span></td>
				</c:if> 
				<c:if test="${!empty storage.stockCount }">
				<th>添加数量</th>
				<form:hidden path="stockCount"  />
				<td><input name="add" id="add"  /><span class="red_star">*</span></td>
				</c:if> 
			</tr>
			<tr>
				<th>备注</th>
				<td><form:input path="memo"/><span class="red_star">*</span></td>
			</tr>
		</table>
		<br /><br>
  	</form:form>
  </body>
</html>
