<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구단 관리</title>
<script type="text/javascript" src = "script/check.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<h1>구단 생성</h1>
<form action = "Create.do" method = "post" name = "check" enctype = "multipart/form-data">
<table>
	<tr>
		<td>구단명</td>
		<td><input type = "text" name = "teamname"></td>
	</tr>
	<tr>
		<td>연고지</td>
		<td><input type = "text" name = "country"></td>
	</tr>
	<tr>
		<td>홈 구장</td>
		<td><input type = "text" name = "homeground"></td>
	</tr>
	<tr>
		<td>감독명</td>
		<td><input type = "text" name = "coach"></td>
	</tr>
	<tr>
		<td>선수단 정보(url)</td>
		<td><input type = "text" name = "players"></td>
	</tr>
	<tr>
		<td>구단 로고</td>
		<td><input type = "file" name = "picture"></td>
	</tr>
	<tr>
		<td colspan="2"><h5>(이미지 파일은 5MB 이하로 업로드 해주세요.)</h5>
		</td>
	</tr>
</table>
	<input type = "submit" value = "작성" onclick = "return CreateCheck()">
	<input type = "reset" value = "다시작성">
	<input type = "button" value = "목록" onclick = "location.href='Select.do?action=selectAll'">
</form>

</body>
</html>