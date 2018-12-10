function open_win(url,name) {
	window.open(url,name, "height=400 , width=500");
}

function cancel_ok() {
	window.open("about:blank","_self").close();
}

function com_check() {
	if(document.check.c_content.value.length == 0) {
		alert("댓글 내용을 입력해주세요.")
		return false;
	}
}

function board_check() {
	if(!document.check.title.value) {
		alert("제목을 입력해주세요.");
		return false;
	}
	if(!document.check.pass.value) {
		alert("비밀번호를 입력해주세요.");
		return false;
	}
}

function board_check1() {
	if(!document.check.r_title.value) {
		alert("제목을 입력해주세요.");
		return false;
	}
	if(!document.check.r_pass.value) {
		alert("비밀번호를 입력해주세요.");
		return false;
	}
}