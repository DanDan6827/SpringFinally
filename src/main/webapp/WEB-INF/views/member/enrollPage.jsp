<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
table th{background-color: #99ffff;}
table#enrollFoarm{border: 2px solid navy;}

</style>
<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
//전송값 유효성 검사 체크용 함수
//validation(유효성 검사): 서버즉 컨트롤러로 전송할 값들이 요구한 조건을 모두 
//						   만족하였는지 검사하는 것
function validate() {
	//암호와 암호확인이 일치하는지 검사
	var pwdValue = document.getElementById("userpwd").value;
	var pwdValue2 = document.getElementById("userpwd2").value;
	
	if (pwdValue !== pwdValue2) {
		alert("암호와 암호확인의 입력값이 일치하지 않습니다.");
		document.getElementById("userpwd2").select();
		return false;//전송취소처리
	}
	return true; //전송 처리
}
//id중복확인용 함수
function dupidCheck() {
	$("#msg").empty();
	$.ajax({
		url:"idCheck.do",
		method:"post",
		data:{id:$("#userid").val() },
		success: function(data) {
			console.log("success:"+data);
			if (data=="ok") {
				$("#msg").append("<p style='color: lightgreen;'>사용가능 아이디입니다.</p>");
				$("#userpwd").focus();
			}else{
				$("#msg").append("<p style='color: red;'>사용중인 아이디입니다.</p>");
				$("#userid").select();
			}
		},
		error: function(jqXHR,textstatus,errorthrown) {
			console.log("error:"+jqXHR+","+textstatus+","+arrorthrown);
		}
		
	});//$.ajax();
	
	//클릭 이벤트가 전달되어서 submit이 동작되지 않게 함
	return false;//클릭 이벤트 취소되게 함
	
	//function dupidCheck()
}
</script>
</head>
<body>
<c:import url="/WEB-INF/views/common/header.jsp" />
<h1 align="center">회원 가입 페이지</h1>
<br>
<form action="anroll.do" method="post" onsubmit="return validate()">
<table id="enrollFoarm" align="center" width="500" cellspacing="5" cellpadding="0">
	<tr>
		<th colspan="2">회원 정보를 입력해 주세요.(* 표시는 필수입력 항목입니다.)</th>
		
	</tr>
	<tr><td width="120">*이름</td><td><input type="text" name="name" required></td></tr>
	<tr><td id="msg">*아이디</td><td><input type="text" name="id" id="userid" required> &nbsp;<input type="button" value="중복체크" onclick="return dupidCheck()"></td></tr>
	<tr><td>*암 호</td><td><input type="password" name="pw" id="userpwd" required></td></tr>
	<tr><td>*암 호확인</td><td><input type="password" id="userpwd2" required></td></tr>
	<tr><td>나이</td><td><input type="number" name="age"></td></tr>
	<tr><td>주소</td><td><input type="text" name="addr"></td></tr>
	<tr><td colspan="2"><input type="submit" value="회원가입">&nbsp;<input type="reset" value="작성취소"></td> </tr>
</table>
</form>
</body>
</html>