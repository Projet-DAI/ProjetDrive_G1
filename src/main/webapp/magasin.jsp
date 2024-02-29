<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Choisir Magasin</title>
</head>
<body>
	<!-- 在JSP页面中的表单 -->
	<form action="RecommendationServlet" method="post">
	    <label for="location">请输入地点：</label>
	    <input type="text" id="location" name="location" required>
	    <button type="submit">提交</button>
	</form>
	
</body>
</html>