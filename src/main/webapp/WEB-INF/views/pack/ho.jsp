<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="/upload/files" enctype="multipart/form-data">
	    <input type="file" name="files" multiple><br/><br/>
	    <button type="submit">Upload</button>
	</form>
</body>
</html>