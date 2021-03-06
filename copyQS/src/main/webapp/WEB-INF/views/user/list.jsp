<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>用户管理</title>
</head>

<body>
	<c:if test="${not empty sucMsg}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${sucMsg}</div>
	</c:if>
	<c:if test="${not empty errMsg}">
		<div id="message" class="alert alert-error"><button data-dismiss="alert" class="close">×</button>${errMsg}</div>
	</c:if>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>登录名</th><th>用户名</th><th>注册时间<th>管理</th></tr></thead>
		<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><a href="${ctx}/user/update/${user.id}">${user.loginName}</a></td>
				<td>${user.name}</td>
				<td>
					<fmt:formatDate value="${user.registerDate}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td><a href="${ctx}/user/delete/${user.id}">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div><a class="btn" href="${ctx}/register">注册</a></div>
</body>
</html>
