<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script type="text/javascript" src="script/board.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<h1>게시글 작성</h1>
<form action = "BoardServlet" method = "post" name="check">
<input type = "hidden" name = "command" value = "board_write">
<table border= "1">
	<tr>
		<td>제목</td>
		<td><input type = "text" name = "title">
			* 필수</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type = "password" name = "pass">
			* 필수  (게시물 수정 및 삭제시 필요합니다.) </td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea rows="15" cols="70" name = "content"></textarea></td>
	</tr>
</table>
<br><br>
<input type = "submit" value = "작성" onclick = "return board_check()">
<input type = "reset" value = "다시 작성">
</form>
</body>
</html>