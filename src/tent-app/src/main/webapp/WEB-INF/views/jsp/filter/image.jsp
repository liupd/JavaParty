<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>image.jsp</title>
</head>
<body>
	<img width="200" src="<%=request.getContextPath()%>/images/mm.jpg" />
	<a href="${ pageContext.request.requestURI }">刷新</a>
	<a href="javascript:window.open('<%=request.getContextPath()%>/images/winter.jpg');"
		onclick="window.open('<%=request.getContextPath()%>/images/winter.jpg'); return false; ">直接访问</a>
	<hr />
	request.getHeader("referer"): ${ header['referer'] }
</body>
</html>
