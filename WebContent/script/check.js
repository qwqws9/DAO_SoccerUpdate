function CreateCheck() {
	if(!document.check.teamname.value){
		alert("구단명을 입력해 주세요.")
		check.teamname.focus();
		return false;
	}
	if(document.check.country.value.length == 0){
		alert("연고지를 입력해 주세요.")
		check.country.focus();
		return false;
	}
	if(document.check.homeground.value.length == 0){
		alert("홈구장을 입력해 주세요.")
		check.homeground.focus();
		return false;
	}
	if(document.check.coach.value.length == 0){
		alert("감독명을 입력해 주세요.")
		check.coach.focus();
		return false;
	}
	if(document.check.players.value.length == 0){
		alert("선수단정보를 입력해 주세요.")
		check.players.focus();
		return false;
	}
	return true;
}

function loginCheck() {
	if(!document.check.id.value) {
		alert("아이디를 입력해 주세요.");
		check.id.focus();
		return false;
	}
	if(!document.check.pw.value) {
		alert("비밀번호를 입력해 주세요.")
		check.pw.focus();
		return false;
	}
	return true;
}

function idCheck() {
	if (!document.check.id.value) {
		alert('아이디를 입력해 주세요.');
		document.check.id.focus();
		return;
	}
	var url = "idCheck.do?action=check&id=" + document.check.id.value;
	window.open(url,"_blank","width=500, height=400");
}

function ok() {
	opener.check.id.value=document.check.id.value;
	opener.check.cid.value=document.check.id.value;
	self.close();
}

function signCheck() {
	if(!document.check.id.value){
		alert("아이디를 입력해 주세요.")
		check.id.focus();
		return false;
	}
	if(!document.check.pw.value){
		alert("암호를 입력해 주세요.")
		check.pw.focus();
		return false;
	}
	if(document.check.pw.value != document.check.pwd.value ){
		alert("암호가 일치하지 않습니다.")
		check.pw.focus();
		return false;
	}
	if(!document.check.cid.value) {
		alert("아이디 중복체크를  해주세요.")
		return false;
	}
	if(document.check.grade.value == 0) {
		if(!document.check.admin.value){
			var url = "idCheck.do?action=admin";
			window.open(url,"_blank","width=500, height=400");
			return false;
		}
		
	}
	return true;
}
	
function admin() {
	if(!document.check.pw.value) {
		alert("관리자 암호를 입력해 주세요.")
		check.pw.focus();
		return false;
	}
}

function noAdmin() {
	window.open("about:blank","_self").close();
}
function yesAdmin() {
	opener.check.admin.value = 1;
	window.open("about:blank","_self").close();
}

function updateCheck() {
	if(!document.check.pw.value){
		alert("암호를 입력해 주세요.")
		check.pw.focus();
		return false;
	}
	if(document.check.pw.value != document.check.pwd.value ){
		alert("암호가 일치하지 않습니다.")
		check.pw.focus();
		return false;
	}
}

function autoLogin1(check) {
	if(check.checked == true){
	alert("공용 컴퓨터에서는 권장하지 않습니다.")
	}
}