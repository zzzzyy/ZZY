<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript">
	$(function(){
		
		$("#new").click(function(){
			window.location.href = "${ctp}/user/";
			return false;
		});
		
		$("img[id^='delete-']").click(function(){
			var custName = $(this).prev(":hidden").val();
			var flag = confirm("确定要删除" + custName + "的信息吗?");
			
			if(flag){
				$("#_method").val("DELETE");
				var id = $(this).next(":hidden").val();
				var action = "${ctp}/user/" + id;
				$("#hiddenForm").attr("action",action).submit();
			}
			
			return false;
		});
	})
</script>
</head>

<body class="main">
	<form id="command" action="${ctp}/user/list" method="post">
		<div class="page_title">
			用户管理
		</div>
		<div class="button_bar">
			<button class="common_button" id="new">新建</button>
			<button class="common_button" onclick="document.forms[0].submit();">
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					用户名
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_name" />
				</td>
				<th class="input_title">
					状态
				</th>
				<td class="input_content">
					<select name="search_EQ_enabled">
						<option value="">
							全部
						</option>
						<option value="1">
							正常
						</option>
						<option value="0">
							已删除
						</option>
					</select>
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${page != null && page.totalElements > 0 }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th class="data_title" style="width: 40px;">
						编号
					</th>
					<th class="data_title" style="width: 50%;">
						用户名
					</th>
					<th class="data_title" style="width: 20%;">
						状态
					</th>
					<th class="data_title">
						操作
					</th>
				</tr>
				<c:forEach var="item" items="${page.content }">
					<tr>
						<td class="data_cell" style="text-align: right; padding: 0 10px;">
						${item.id}
						</td>
						<td class="data_cell" style="text-align: center;">
						${item.name}
						</td>
						<td class="data_cell">
						${item.enabled == 1 ? "有效" : "无效"}
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctp}/user/${item.id }'" 
								title="编辑" src="${ctp}/static/images/bt_edit.gif"
								class="op_button" />
							
							<input type="hidden" value="${item.role.name }"/>
							<img id="delete-${item.id }" title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
							<input type="hidden" value="${item.id }"/>
							
						</td>
					</tr>
				</c:forEach>
			</table>
			<atguigu:page page="${page }"></atguigu:page>
		</c:if>
		<c:if test="${page == null || page.totalElements == 0 }">
			没有任何数据
		</c:if>
		
	</form>
</body>
</html>
