<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>产品管理</title>
	<script type="text/javascript">
		$(function(){
			$("#new").click(function(){
				window.location.href = "${ctp}/product/";
				return false;
			});
			$("img[id^='delete-']").click(function(){
				var custName = $(this).prev(":hidden").val();
				var flag=confirm("确认要删除"+custName+"吗?");
				if(flag){
					$("#_method").val("DELETE");
					var id = $(this).next(":hidden").val();
					var action = "${ctp}/product/" + id;
					$("#hiddenForm").attr("action",action).submit();
				}
				return false;
			})
		})
	</script>
</head>

<body class="main">
	<form id="command" action="${ctp}/product/list" method="post">
		
		<div class="page_title">
			产品管理
		</div>
	
		<div class="button_bar">
			<button class="common_button" id="new">
				产品添加
			</button>
			<button class="common_button" onclick="document.forms[0].submit();">
				查询
			</button>
		</div>
	
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					名称
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_custName" />
				</td>
				<th class="input_title">
					型号
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_title" />
				</td>
				<th class="input_title">
					批次
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_contact" />
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
						名称
					</th>
					<th>
						型号
					</th>
					<th>
						等级/批次
					</th>
					<th>
						单位
					</th>
					<th>
						单价（元）
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
						<td class="list_data_text">${item.name }</td>
						<td class="list_data_text">${item.type }</td>
						<td class="list_data_text">${item.batch }</td>
						<td class="list_data_text">${item.unit }</td>
						<td class="list_data_text">${item.price }</td>
						<td class="list_data_text">${item.memo }</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctp}/product/dispatch?id=150'" 
								title="指派" src="${ctp}/static/images/bt_linkman.gif" class="op_button" />
							
							<img onclick="window.location.href='${ctp}/product/${item.id }'" 
								title="编辑" src="${ctp}/static/images/bt_edit.gif"
								class="op_button" />
							
							<input type="hidden" value="${item.name }"/>
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
