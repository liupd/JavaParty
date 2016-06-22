<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body, td, div, input {
	font-size: 12px; 
}
.error {
	padding: 5px; 
	border: 1px solid #FF0000; 
	background: url(images/error.gif) 8px 5px no-repeat #FFEEFF; 
	padding-left: 30px; 
}
</style>
</head>
<body>

<div class="error">
${ message } 
</div>

<form action="">
	<table>
		<tr>
			<td>帐号</td>
			<td><input type="text" name="account" /></td>
		</tr>
		<tr>
			<td>密码</td>
			<td><input type="password" name="password" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" value=" 登录 " /></td>
		</tr>
	</table>
</form>

</body>
</html>