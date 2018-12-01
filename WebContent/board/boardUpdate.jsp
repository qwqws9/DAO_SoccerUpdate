<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script type="text/javascript" src = "script/board.js"></script>
</head>
<body>
<h1>게시글 수정</h1>
<form action = "BoardServlet" method = "post">
<input type = "hidden" name = "command" value = "board_update">
<input type = "hidden" name = "num" value = "${param.num }">
<table border="1">
	<tr>
		<td>제목</td>
		<td><input type = "text" name = "title" value = "${sVo.title }"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type = "password" name = "pass"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea rows="15" cols="70" name = "content">${sVo.content }</textarea></td>
	</tr>
</table>
<br><br>
<input type = "submit" value = "확인">
<input type = "reset" value = "다시작성">
<input type = "button" value = "목록" onclick = "location.href ='BoardServlet?command=board_list'">
</form>

</body>
</html>