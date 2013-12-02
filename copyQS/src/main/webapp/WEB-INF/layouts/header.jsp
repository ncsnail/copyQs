<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div id="header">
	<div id="title">
	<h3> 
	<a href="${ctx}">CopySQ Pro</a>
	<shiro:authenticated>
	<span class="btn-group pull-right">
	  <a class="btn " href="#"><i class="icon-user "></i><shiro:principal property="name"/></a>
	  <a class="btn  dropdown-toggle" data-toggle="dropdown" href="#"><span class="caret"></span></a>
	  <ul class="dropdown-menu">
	    <li><a href="${ctx}/profile"><i class="icon-pencil"></i> Edit Profile</a></li>
	    <li><a href="${ctx}/logout"><i class="icon-remove"></i> Logout</a></li>
	    <shiro:hasRole name="admin">	
	    <li class="divider"></li>
	    <li><a href="${ctx}/user"><i class="i"></i> User Management</a></li>
	    </shiro:hasRole>
	  </ul>
	</span>
	</shiro:authenticated>
	</h3>
	</div>
</div>