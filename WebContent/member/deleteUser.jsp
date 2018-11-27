<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 삭제</title>
</head>
<body>
<h2>정말 탈퇴하시겠습니까?</h2>
<form action = "DeleteUser.do" method = "post" >
	<tr>
		<td><input type = "submit" value = "탈퇴"></td>
		<td><input type = "button" value = "취소" onclick = "location.href='Select.do?action=selectAll'"></td>
	</tr>
</form>
</body>
</html>