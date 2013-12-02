<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<shiro:authenticated>
<div id="menu">
<span style="margin-left:10px"><a href="${ctx}/task">Task List</a></span>
<shiro:hasRole name="admin">
<span style="margin-left:10px"><a href="${ctx}/user">User Management</a></span>
</shiro:hasRole>
</div>
</shiro:authenticated>