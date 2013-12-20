<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%response.setStatus(200);%>

<%
	// record error message
	Throwable ex =  (Throwable) request.getAttribute("javax.servlet.error.exception");
	if(ex != null){
		Logger log =  LoggerFactory.getLogger("500.jsp");
		log.error(ex.getMessage(),ex);
	}

%>

<!DOCTYPE html>
<html>
<head>
	<title>500</title>
</head>

<body>
	<h2>哦喔，系统太累了，要休息，休息一会！</h2>
</body>
</html>
