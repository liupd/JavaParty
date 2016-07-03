<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<body>
<form method="post" enctype="multipart/form-data"
      action="${pageContext.request.contextPath}/uploadfile/upload/files">
                           选择文件:<input type="file" name="files"><br />
		选择文件:<input type="file" name="files"><br /> 
		选择文件:<input type="file" name="files"><br /> 
                           名称：<input type="text" name="name"><br />
      <input type="submit" value="提交">
</form>
</body>
</html>