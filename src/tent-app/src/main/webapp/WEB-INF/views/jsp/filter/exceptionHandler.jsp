<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.zp.tent.common.exception.BusinessException" %>
<%@ page import="com.zp.tent.common.exception.AccountException"%>
<%
	String action = request.getParameter("action");

	if("businessException".equals(action)){
		throw new BusinessException("业务操作失败. ");
	}
	else if("accountException".equals(action)){
		throw new AccountException("您需要登陆后再进行此项操作. ");
	}
	else if("exception".equals(action)){
		Integer.parseInt("");
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<a href="${ pageContext.request.requestURI }?action=businessException">test BusinessException</a> <br/>
<a href="${ pageContext.request.requestURI }?action=accountException">test AccountException</a> <br/>
<a href="${ pageContext.request.requestURI }?action=exception">test Exception</a> <br/>

</body>
</html>