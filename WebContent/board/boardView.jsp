<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script type="text/javascript" src= "script/board.js"></script>
</head>
<body>
<table border= "1">
	<tr>
		<td>제목</td>
		<td>${sVo.title }</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${sVo.mid }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${sVo.content }</td>
	</tr>

</table>
<br><br>
<input type = "button" value = "목록" onclick = "location.href='BoardServlet?command=board_list'">
<c:if test = "${sessionId.id == sVo.mid }">
<input type = "button" value = "수정" onclick = "open_win('BoardServlet?command=board_pass&num=${sVo.num}','update')">
<input type = "button" value = "삭제" onclick = "open_win('BoardServlet?command=board_pass&num=${sVo.num}','delete')">
</c:if>


</body>
</html>