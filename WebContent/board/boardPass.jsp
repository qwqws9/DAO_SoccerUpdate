<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
<h1>비밀번호 입력</h1>
<form action = "BoardServlet" method = "post" name = "check">
<input type = "hidden" name = "command" value = "board_pass_check">
<input type = "hidden" name = "num" value = "${param.num }">

<input type = "password" name = "pass" > <!--자바스크립트 유효성 체크할것  -->
<input type = "submit" value = "확인">
${message }
</form>

</body>
</html>