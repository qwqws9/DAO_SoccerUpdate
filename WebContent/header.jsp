<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" type = "text/css" href = "css/layout.css">
</head>
<body>
<table border="1" id="hd">
	<tr>
		<c:if test = "${sessionId != null }">
			<th>${sessionId.id }님 환영합니다.</th>
			<th>등급 :<c:choose>
					<c:when test= "${sessionId.grade == 0  }">관리자
					</c:when>
					<c:otherwise>일반회원</c:otherwise>
				</c:choose>
			</th>
			<th>
				<c:choose>
					<c:when test = "${param.command == null }">
				<a href= "BoardServlet?command=board_list&page=1">자유게시판</a>
					</c:when>
					<c:otherwise>
				<a href= "Select.do?action=selectAll">구단정보</a>
					</c:otherwise>
				</c:choose>
			</th>
			<th>
				<a href= "Mypage.do">마이페이지</a>
			</th>
			<th>
				<a href = "Logout.do?action=session">로그아웃</a>
			</th>
		</c:if>
		
		<c:if test = "${sessionId == null }">
			<th></th>
			<th></th>
			<th></th>
			<th>
				<a href= "Login.do?action=login">로그인</a>
			</th>
			<th>
				<a href = "Login.do?action=signup">회원가입</a>
			</th>
		</c:if>
	</tr>
</table>
</body>
</html>