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
<jsp:include page="../header.jsp"></jsp:include>
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
<table>
	<tr>
		<th colspan="2">댓글 목록</th>
	</tr>
	<c:choose>
		<c:when test="${message != null }">
			<tr>
				<td>${message }</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="list" items="${list }">
				<tr>
					<td><font size="1.5"><b>${list.cid }</b>
						${list.ctime }
						<c:if test = "${sessionId.id == list.cid }">
							<b>
								<a href="javascript:open_win('BoardServlet?command=comment_edit_delete&cnum=${list.cnum }','noname')">[수정/삭제]</a>
							</b>
						</c:if>
						</font>
						<br>
							${list.ccontent }
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
<form action = "BoardServlet" method = "post" name = "check">
<input type = "hidden" name = "command" value = "comment_write">
<input type = "hidden" name = "pnum" value = "${param.num }">
<table>
	<tr>
		<th>댓글</th>
		<td><textarea rows="3" cols="40" name = "c_content"></textarea></td>
	
		<td><input type = "submit" value = "댓글달기" onclick = "return com_check()"></td>
	</tr>
</table>
</form>

<c:forEach var="i" begin = "1" end ="${totalCount }" step="1">
	<a href="BoardServlet?command=board_view&num=${param.num }&page=${i}">[${i}]</a>
</c:forEach>

<br><br>
<input type = "button" value = "목록" onclick = "location.href='BoardServlet?command=board_list'">
<c:if test = "${sessionId.id == sVo.mid }">
<input type = "button" value = "수정" onclick = "open_win('BoardServlet?command=board_pass&num=${sVo.num}','update')">
<input type = "button" value = "삭제" onclick = "open_win('BoardServlet?command=board_pass&num=${sVo.num}','delete')">
<input type = "button" value = "답글" onclick = "open_win('BoardServlet?command=board_reply_form&num=${sVo.num}','reply')">
</c:if>


</body>
</html>