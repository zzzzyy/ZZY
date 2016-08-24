<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>客户服务管理</title>
	<script type="text/javascript">
		$(function(){
			
			$(".op_button").click(function(){
				var label = $(this).attr("name");
				var id =	$(this).attr("id");
				var flag = confirm("确定要删除" + label + "吗?");
				if(flag){
					window.location.href="${ctp}/service/delete?id="+id;
				}
					return false;
			})
			
			
			
			$("#allotTo").change(function(){
				
				var val = $(this).val();
				if(val != ""){
					var label = $(this).find("option:selected").text();
					var flag = confirm("确定要分配给" + label + "吗?");
					
					if(flag){
						var $tr = $(this).parent().parent();
						var id = $(this).prev(":hidden").val();
						var url = "${ctp}/service/allot/ajxx";
						var args = {"id":id, "allotTo.id":val, "allotDate":new Date().format("yyyy-MM-dd")};
						
						$.post(url, args, function(data){
							if(data == "1"){
								alert("分配成功!");
								$tr.remove();
							}else{
								alert("分配失败!");
							}
						});
					}else{
						$(this).find("option[value='']").attr("selected", "selected");
					}
				}
			});
		})
	</script>
</head>
<body>

	<div class="page_title">
		客户服务管理
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="">
			新建
		</button>
		<button class="common_button" onclick="document.forms[1].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp }/service/allot/list/1" method="POST">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					服务类型
				</th>
				<td>
					<input type="text" name="search_LIKES_serviceType" />
				</td>
				<th>
					概要
				</th>
				<td>
					<input type="text" name="search_LIKES_serviceTitle" />
				</td>
			</tr>
			<tr>
				<th>
					客户
				</th>
				<td>
					<input type="text" name="search_LIKES_customerName" />
				</td>
				<th>
					创建时间
				</th>
				<td>
					<input type="text" name="search_LIKES_createDateq" size="10" />
					-
					<input type="text" name="search_LIKES_createDateh" size="10" />
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${page != null && page.totalElements > 0 }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						编号
					</th>
					<th>
						服务类型
					</th>
					<th>
						概要
					</th>
					<th>
						客户
					</th>
					<th>
						创建人
					</th>
					<th>
						创建时间
					</th>
					<th>
						分配给
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach var="service" items="${page.content }">
					<tr>
						<td class="list_data_number">
							${service.id}
						</td>
						<td class="list_data_text">
							${service.serviceType}
						</td>
						<td class="list_data_ltext">
							${service.serviceTitle}
						</td>
	
						<td class="list_data_text" >
							${service.customer.name}
						</td>
						<td class="list_data_text">
							${service.createdby.name}
						</td>
						<td class="list_data_text">
							<fmt:formatDate value="${service.createDate }" pattern="yyyy-MM-dd"/>
						</td>
	
						<td class="list_data_text">
							<input type="hidden" name="id" value="${service.id }"/>
							<select name="allotTo" id="allotTo">
								<option value="">
									未指定
								</option>
								<c:forEach items="${users }" var="user">
									<option value="${user.id}">${user.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="list_data_op">
							<img id=${service.id } title="删除" src="${ctp }/static/images/bt_del.gif" class="op_button" name="${service.customer.name}"/>
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
