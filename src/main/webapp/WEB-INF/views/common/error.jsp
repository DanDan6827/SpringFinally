<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 style="color: red; margin: auto; text-align: center;">에러 페이지</h1>
<h3>에러 메세지 :${requestScope.message }</h3>
<br>
<a href="${pageContext.servletContext.contextPath }/main.do">시작페이지로 이동</a>
</body>
</html>