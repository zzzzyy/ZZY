<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>制定计划</title>
	<script type="text/javascript">
		$(function(){
			
			function update(button){
				var id = $(button).attr("id");
				id = id.split("-")[1];
				var todo = $("#todo-" + id).val();
				var url = "${ctp}/plan/makes-ajax";
				var args = {"id":id, "todo":todo,"chance.id": "${chance.id}"};
				
					$.post(url, args, function(data){
						if(data == "1"){
							alert("修改成功!");
						}else{
							alert("修改失败!");
						}
				
				});
				
					return false;
			};		
			function deleteq(button){
				var id = $(button).attr("id");
				id = id.split("-")[1];
				var url = "${ctp}/plan/delete-ajax";
				var args = {"id":id};
				$.post(url, args, function(data){
					if(data == "1"){
						$("#plan-" + id).remove();
						alert("删除成功!");
					}else{
						alert("删除失败!");
					}
				});
				
				return false;
				
			}
			$("button[id^='delete']").click(function(){
				
				deleteq(this);
				return false;
			});	
			
			$("button[id^='save']").click(function(){
				 update(this);
				return false;
			});
			$("#newplan").click(function(){

				var date = $("#date").val();
				var todo = $("#todo").val();
				var url = "${ctp}/plan/savePlan";
				
				var args = {"date":date,"todo":todo,"chance.id": "${chance.id}","time":new Date()}
				$.post(url, args, function(data){
					var reg = /^\d+\$/;
					if(reg.test(data)){
						var id = parseInt(data);
						if(id > 0){
							var $tr = $("#arr");
							$tr.append("<tr id='plan-" + id + "'><td class='list_data_text'>" + date + "</td>"+"<td class='list_data_ltext'><input size=50 id='todo-" + id + "' type='text' value='" + todo + "'/>"+
							   "<input type='hidden' value='" + id + "'/><button class='common_button' id='saves-" + id + "'>保存</button><input type='hidden' value='" + id + "'/><button class='common_button' id='deletes-" + id + "'>删除</button></td></tr>")
							$tr.find("button[id^='saves']").click(function(){
								update(this);
								return false;
							});
							$tr.find("button[id^='deletes']").click(function(){
								deleteq(this);
								return false;
							});
							
							
							alert("添加成功!");
						}else{
							alert("添加失败!");
						}
					}
				});
				return false;
			});
			
		});
	</script>
</head>

<body class="main">
	<span class="page_title">制定计划</span>
	<div class="button_bar">
		<button class="common_button" id="execute" onclick="window.location.href='${ctp}/plan/execution/${chance.id}'">
			执行计划
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
	</div>
	
		<form action="${ctx }/plan/make" method="post">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>

				<td>
					${chance.id}
				</td>
				<th>
					机会来源
				</th>

				<td>
					${chance.source}
				</td>
			</tr>
			<tr>
				<th>
					客户名称
				</th>
				<td>
					${chance.custName}
				</td>
				<th>
					成功机率（%）
				</th>

				<td>
					${chance.rate}
				</td>
			</tr>
			<tr>
				<th>
					概要
				</th>
				<td colspan="3">
					${chance.title}
				</td>
			</tr>
			<tr>
				<th>
					联系人
				</th>

				<td>
					${chance.contact}
				</td>
				<th>
					联系人电话
				</th>

				<td>
					${chance.contactTel}
				</td>
			</tr>
			<tr>
				<th>
					机会描述
				</th>
				<td colspan="3">
					${chance.description}
				</td>
			</tr>
			<tr>
				<th>
					创建人
				</th>
				<td>
					${chance.createBy.name}
				</td>
				<th>
					创建时间
				</th>
				<td>
					<fmt:formatDate value="${chance.createDate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<th>
					指派给
				</th>
				<td>
					${chance.designee.name}
				</td>

			</tr>
		</table>

		<br />
		
		<table class="data_list_table" border="0" cellPadding="3"
			cellSpacing="0" id=arr>
			<tr>
				<th width="200px">
					日期
				</th>
				<th>
					计划项
				</th>
			</tr>
			<c:if test="${not empty chance.salesPlans }">
				<c:forEach items="${chance.salesPlans }" var="plan">
					<tr id="plan-${plan.id}">
						<td class="list_data_text">
							<fmt:formatDate value="${plan.date }" pattern="yyyy-MM-dd"/>
							&nbsp;
						</td>
						<td class="list_data_ltext">
							<c:if test="${plan.result == null }">
								<input type="text" size="50"
									value="${plan.todo}" id="todo-${plan.id}"/>
								<button class="common_button" id="save-${plan.id}">
									保存
								</button>
								<button class="common_button" id="delete-${plan.id}">
									删除
								</button>
							</c:if>
							<c:if test="${plan.result != null }">
								<input type="text" size="50"
									value="${plan.todo}" readonly="readonly"/>
								<input type="text" size="50"
									value="${plan.result}" readonly="readonly"/>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div class="button_bar">
			<button class="common_button" id="newplan">
				新建
			</button>
		</div>
		<input type="hidden" name="chance.id" value="${chance.id}" />
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					日期
					<br />
					(格式: yyyy-mm-dd)
				</th>
				<td>
					<input type="text" name="date" id="date" />
					&nbsp;
				</td>
				<th>
					计划项
				</th>
				<td>
					<input type="text" name="todo" size="50" id="todo" />
					&nbsp;
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
