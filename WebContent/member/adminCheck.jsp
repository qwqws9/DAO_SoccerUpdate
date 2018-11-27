<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자</title>
<script type="text/javascript" src = "script/check.js"></script>
</head>
<body>
<h2>관리자 암호 입력</h2>
<form action = "idCheck.do" method = "post" name = "check">

		<input type = "password" name = "pw">
		<input type = "submit" value = "확인" onclick = "return admin()">


		<br><br>
		<c:if test = "${ok == -1 }">
			관리자가 아닙니다. 일반회원으로 등록해 주세요.
			<input type = "button" value = "닫기" onclick = "noAdmin()">
			</c:if>
		<c:if test = "${ok == 1 }">
			인증되었습니다.
			<input type = "button" value = "닫기" onclick = "yesAdmin()">
			</c:if>
		

</form>
	


</body>
</html>