<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix ="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
<h1>게시글 리스트</h1>
<table border= "1">
<tr>
	<td colspan = "5" align = "right">
		<a href= "BoardServlet?command=board_write_form">글쓰기</a></td>
</tr>
<tr>
	<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th>
	<c:forEach var = "board" items = "${list }">
	<tr>
		<td>${board.num }</td>
		<td><a href="BoardServlet?command=board_view&num=${board.num}">${board.title }</a></td>
		<td>${board.mid }</td>
		<td><fmt:formatDate value = "${board.writedate }"/></td>
		<td>${board.hit }</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>