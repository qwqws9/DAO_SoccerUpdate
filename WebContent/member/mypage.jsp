<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script type="text/javascript"></script>
<!-- <link rel = "stylesheet" type = "text/css" href = "css/layout.css"> -->
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<h1>마이페이지</h1>
<input type = "button" value = "정보 수정" onclick = "location.href='UpdateUser.do?id=${sessionId.id}'">
<input type = "button" value = "회원 탈퇴" onclick = "location.href='DeleteUser.do?id=${sessionId.id}'">

</body>
</html>