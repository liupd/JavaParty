<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>sdfsd</p>
	<p>sadfsdf</p>
	<ol>
		<li>sadf</li>
		<li>asdfsadf</li>
		<li>sdf</li>
		<li>sdfsadfasdf</li>
	</ol>
	<img src="<%=basePath%>/images/a.gif">
	<img src="<%=basePath%>/images/b.gif">
</body>
</html>


