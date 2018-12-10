<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구단 정보</title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<h1>구단 정보</h1>	
<table border="1">
	<tr>
	<c:if test = "${sessionId.grade == 0 }">
		<td colspan="8" align = "right">
		<a href="Create.do">팀 등록</a>
		</td>
	</c:if>
	</tr>
	<tr>
		<th>번호</th><th>팀 명</th><th>연고지</th><th>홈구장</th><th>감독명</th><th>선수단 정보</th><th>수정</th><th>삭제</th>
	</tr>
		<c:if test = "${message == null }">	
			
				<c:forEach var = "team" items = "${list }">
		<tr>
			<td>${team.code }</td>
			<td><a href = "Select.do?action=choice&code=${team.code}">${team.teamname }</a></td>
			<td>${team.country }</td>
			<td>${team.homeground }</td>
			<td>${team.coach }</td>
			<td><input type ="button" value = "바로가기" onclick ="location.href='${team.players }'"></td>
		<c:choose>
			<c:when test="${sessionId.grade == 0 }">
			<td><a href = "Update.do?code=${team.code }">팀 수정</a></td>
			<td><a href = "Delete.do?code=${team.code }">팀 삭제</a></td>
			</c:when>
			<c:otherwise>
			<td></td>
			<td></td>
			</c:otherwise>
		</c:choose>
			

		</tr>
				</c:forEach>	
		</c:if>
</table>
		<c:if test = "${message != null }">
			<h2>${message }</h2>
		</c:if>
</body>
</html>