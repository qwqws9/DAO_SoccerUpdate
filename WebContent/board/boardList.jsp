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
<jsp:include page="../header.jsp"></jsp:include>
<h1>자유게시판</h1>
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
		<td><a href="BoardServlet?command=board_view&num=${board.num}">
		<c:if test = "${board.rindent != 0 }">
			<c:forEach var="i" begin="0" end="${board.rindent }" step="1">
						&nbsp;&nbsp;
			</c:forEach>
			<img src="upload/reply.gif"/>
		</c:if>
		${board.title }
			<c:forEach var = "clist" items = "${clist }">
				<c:if test = "${clist.num == board.num }">
					<c:if test = "${clist.ccount != 0 }">
						[${clist.ccount}]
					</c:if>
				</c:if>
			</c:forEach>
			</a></td>
		<td>${board.mid }</td>
		<td><fmt:formatDate value = "${board.writedate }"/></td>
		<td>${board.hit }</td>
	</tr>
	</c:forEach>
</table>
<c:if test = "${countPageView > 4 }">
	◀
	</c:if>
	<c:forEach var="i" begin="1" end="${countPageView }" step="1">
		<a href="BoardServlet?command=board_list&page=${i }">[${i}]</a>
	</c:forEach>
	<c:if test = "${countPageView > 4 }">
	▶
	</c:if>
</body>
</html>