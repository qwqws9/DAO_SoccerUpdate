<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<script type="text/javascript" src = "script/check.js"></script>
</head>
<body>
<h1>회원정보 수정</h1>
<form action = "UpdateUser.do" method = "post" name = "check" enctype = "multipart/form-data">
	<tr>
		<td>
			<c:choose>
				<c:when test = "${empty sVo.profile }">
					<img src = "upload/no.gif">
				</c:when>
				<c:otherwise>
					<img src = "upload/${sVo.profile }">
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
<table>	
	<tr>
		<td>아이디</td>
		<td><input type = "text" name = "id" value = "${sVo.id }" readonly>
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
		<td><input type = "text" name = "email" value = "${sVo.email }"></td>
	</tr>
	<tr>
		<td>휴대폰번호</td>
		<td><input type = "text" name = "phone" value = "${sVo.phone }"></td>
	</tr>
	<tr>
		<td>프로필 사진</td>
		<td><input type = "file" name = "profile" value = "${sVo.profile }"></td>
		
	</tr> 	
		<td colspan="2" align="center">
			<h4>사진 변경시에만 업로드해주세요.</h4></td>
</table>
	<input type = "submit" value = "확인" onclick = "return updateCheck()">
	<input type = "button" value = "목록" onclick = "location.href='Select.do?action=selectAll'">
</form>
</body>
</html>