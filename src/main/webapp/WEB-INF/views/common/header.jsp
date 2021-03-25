<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>testapp</title>
<style type="text/css">
div#loginForm{
width: 280px;
font-size: 9pt;
text-align: left;
padding-right: 300px;
float: right;
}
header{margin-left:100px; padding: 0;}
header ul#menubar{
list-style: none;
    width: 1200px;
    margin-left: 800px;
}
header ul#menubar li {
float: left;
width: 120px;
height: 30px;
margin-right: 5px;
padding: 0px;

}
header ul#menubar li a {
text-decoration: none;
width: 120px;
height: 30px;
display: block;
background-color: orange;
text-align: center;
color: navy;
font-weight: bold;
margin: 0px;
text-shadow: 1px 1px 2px white;
padding-top: 5px;
}
header ul#menubar li a:hover{
text-decoration: none;
width: 120px;
height: 30px;
display: block;
background-color: navy;
text-align: center;
color: white;
font-weight: bold;
margin: 0px;
text-shadow: 1px 1px 2px gray;
padding-top: 5px; 
}
hr{clear: both;}/* float속성 해제 */

</style>
</head>
<body>
<header>
	<h1 id="logo">TEST APP</h1>
	<%-- 로그인 상태이면서 관리자가 로그인한 경우 --%>
	<c:if test="${!empty sessionScope.loginMember and loginMember.id eq 'admin' }">
		<ul id="menubar">
			<li><a href="${pageContext.servletContext.contextPath }/ad_mlist.do">회원관리</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/ad_nlist.do">공지사항관리</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/ad_blist.do">자료실관리</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/ad_qalist.do">Q&A관리</a></li>
		</ul>
	</c:if>
	<%-- 로그인 상태가 아닌 경우 --%>
	<c:if test="${empty sessionScope.loginMember }">
		<ul id="menubar">
			<li><a href="${pageContext.servletContext.contextPath }/main.do">홈</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/nlist.do">공지사항</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/blist.do">자료실</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/qalist.do">Q&A</a></li>
		</ul>
	</c:if>
	<c:if test="${empty sessionScope.loginMember }">
	<div id="loginForm">
		<fieldset>
			<legend>로그인하세요</legend>
			<form method="post" action="login.do">
				아이디 : <input type="text" name="id"><br>
				암호 : <input type="password" name="pw">
				
				<input type="submit" value="로그인">
			</form>
			<a href="${pageContext.servletContext.contextPath }/qalist.do">아이디/비밀번호 조회</a> &nbsp;
			<a href="${pageContext.servletContext.contextPath }/enrollPage.do">회원가입</a>
		</fieldset>
	</div>
	</c:if>
	<c:if test="${!empty sessionScope.loginMember }">
	<div id="loginForm">
		<table width="200">
			<tr><td>${ sessionScope.loginMember.name } 님</td>
			
			<td><a href="${pageContext.servletContext.contextPath }/logout.do">로그아웃</a></td></tr>
			<tr>
			<td><a href="${pageContext.servletContext.contextPath }/mylist.do">내정보보기</a></td>
			<td><a href="${pageContext.servletContext.contextPath }/mycontain.do">My자료실</a></td>
			</tr>
		</table>
	</div>
	</c:if>
</header>
<hr>
</body>
</html>