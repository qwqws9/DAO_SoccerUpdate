<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복확인</title>
<script type="text/javascript" src = "script/check.js"></script>
</head>
<body>
<form action = "idCheck.do" method ="get" name ="check">
<input type = "hidden" name = "action" value= "check">
<input type = "text" name = "id" value = "${id }">
<input type = "submit" value = "중복체크"><br>

	<c:if test = "${result == 1 }">
		${id }는 사용가능한 아이디입니다.
		<input type = "button" value = "사용" onclick = "return ok()">
	</c:if>
	<c:if test = "${result == -1 }">
		${id }는 사용중인 아이디입니다.
		<script type="text/javascript">
		opener.check.id.value=""</script>
	</c:if>
</form>

</body>
</html>