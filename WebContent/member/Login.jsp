<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script type="text/javascript" src= "script/check.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<h1>로그인</h1>
<form action = "Login.do" method = "post" name = "check" >
<table>
	<tr>
		<td>아이디</td>
		<td><input type = "text" name = "id" value = "${id }"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type = "password" name = "pw"></td>
	</tr>
</table>
	<input type = "submit" value = "로그인" onclick = "return loginCheck()">
	<input type = "button" value = "목록" onclick = "location.href='Select.do?action=selectAll'">
	<!-- onclick 이벤트시 name값과 메서드명이 같으면 오류발생 -->
	자동로그인<input type = "checkbox" name = "autoLogin" value = "true" onclick = "autoLogin1(this)">
	<br>${message }
</form>

</body>
</html>