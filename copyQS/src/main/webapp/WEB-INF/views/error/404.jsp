<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%response.setStatus(200);%>

<!DOCTYPE html>
<html>
<head>
	<title>404</title>
</head>

<body>
	<h2>哦喔！ 请原谅，你访问的东东不存在哦！</h2>
	<p><a href="<c:url value="/"/>">返回首页</a></p>
</body>
</html>