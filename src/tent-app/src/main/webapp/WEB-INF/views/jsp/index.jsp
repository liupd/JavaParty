<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Tent首页</title>
</head>
<body>
	<h2>Welcome To Tent!</h2>
	<p>Click below button to redirect the result to new page</p>
	<form:form method="GET" action="/tent-app/redirect">
		<table>
			<tr>
				<td><input type="submit" value="Redirect Page" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>