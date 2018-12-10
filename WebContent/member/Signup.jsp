<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script type="text/javascript" src = "script/check.js"></script>
</head>
<body>
<form action = "Signup.do" method = "post" name = "check" enctype = "multipart/form-data">
<jsp:include page="../header.jsp"></jsp:include>
<H1>회원가입 페이지</H1>
<table>
	<tr>
		<td>아이디</td>
		<td><input type = "text" name = "id">
		<input type = "button" name = "idcheck" value = "중복체크" onclick = "idCheck()">
		<input type = "hidden" name = "cid"></td>
	</tr>
	<tr>
		<td>암호</td>
		<td><input type = "password" name = "pw"></td>
	</tr>
	<tr>
		<td>암호 확인</td>
		<td><input type = "password" name = "pwd"></td>
	<tr>
		<td>이메일</td>
		<td><input type = "text" name = "email"></td>
	</tr>
	<tr>
		<td>휴대폰번호</td>
		<td><input type = "text" name = "phone"></td>
	</tr>
	<tr>
		<td>프로필 사진</td>
		<td><input type = "file" name = "profile"></td>
	</tr>
	<tr>
		<td>회원 등급</td>
		<td>관리자<input type = "radio" name ="grade" value ="0">
			일반<input type = "radio" name ="grade" value ="1" checked="checked"></td>
	</tr> 
</table>
	<input type = "hidden" name = "admin">
	<input type = "submit" value = "확인" onclick = "return signCheck()">
	<input type = "button" value = "목록" onclick = "location.href='Select.do?action=selectAll'">
</form>
</body>
</html>