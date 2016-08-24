<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>销售机会管理</title>
	<script type="text/javascript">
		$(function(){
			
			$("#new").click(function(){
				window.location.href = "${ctp}/storage/";
				return false;
			});
			
			$("img[id^='delete-']").click(function(){
				var custName = $(this).prev(":hidden").val();
				var flag = confirm("确定要删除" + custName + "的信息吗?");
				
				if(flag){
					$("#_method").val("DELETE");
					var id = $(this).next(":hidden").val();
					var action = "${ctp}/storage/" + id;
					$("#hiddenForm").attr("action",action).submit();
				}
				
				return false;
			});
		})
	</script>
</head>

<body class="main">
	<form id="command" action="${ctp}/storage/list" method="post">
		
		<div class="page_title">
			销售机会管理
		</div>
	
		<div class="button_bar">
			<button class="common_button" id="new">
				库存添加
			</button>
			<button class="common_button" onclick="document.forms[0].submit();">
				查询
			</button>
		</div>
	
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					产品
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_custName" />
				</td>
				<th class="input_title">
					仓库
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_title" />
				</td>
			</tr>
		</table>
		
		<!-- 列表数据 -->
		<br />
		<c:if test="${empty page.content }">
			没有任何数据信息.
		</c:if>
		<c:if test="${!empty page.content }">
			<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th>
						编号
					</th>
					<th>
						产品名称
					</th>
					<th>
						仓库
					</th>
					<th>
						货位
					</th>
					<th>
						件数
					</th>
					<th>
						备注
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach items="${page.content }" var="item">
					<tr>
						<td class="list_data_number">${item.id }</td>
						<td class="list_data_text">${item.product.name }</td>
						<td class="list_data_text">${item.wareHouse }</td>
						<td class="list_data_text">${item.stockWare }</td>
						<td class="list_data_text">${item.stockCount }</td>
						<td class="list_data_text">${item.memo }</td>
						
						<td class="list_data_op">
							<img onclick="window.location.href='${ctp}/storage/dispatch?id=150'" 
								title="指派" src="${ctp}/static/images/bt_linkman.gif" class="op_button" />
							
							<img onclick="window.location.href='${ctp}/storage/${item.id }'" 
								title="编辑" src="${ctp}/static/images/bt_edit.gif"
								class="op_button" />
							
							<input type="hidden" value="${item.product.name }"/>
							<img id="delete-${item.id }" title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
							<input type="hidden" value="${item.id }"/>
							
						</td>
					</tr>
				</c:forEach>
			</table>
			
			<atguigu:page page="${page }"></atguigu:page>
			
		</c:if>
		
	</form>
	
</body>
</html>
