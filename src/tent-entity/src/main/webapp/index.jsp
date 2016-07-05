<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心标签库 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>显示用户信息</title>
<style type="text/css">
table, td {
	border: 1px solid;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<table>
		<tr>
			<td>用户ID</td>
			<td>用户名</td>
			<td>用户生日</td>
			<td>工资</td>
		</tr>
		<%--遍历lstUsers集合中的User对象 --%>
		<c:forEach var="user" items="${lstUsers}">
			<tr>
				<td>${user.user_id}</td>
				<td>${user.user_name}</td>
				<td>${user.user_birthday}</td>
				<td>${user.user_salary}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>