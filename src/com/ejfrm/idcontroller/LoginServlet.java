package com.ejfrm.idcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ejfrm.DAO.SoccerDAO;
import com.ejfrm.VO.SoccerVO;


@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String url = "index.jsp";
		if (action.equals("login")) {
			url = "member/Login.jsp";
		} else if (action.equals("signup")) {
			url = "member/Signup.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		String url = "member/Login.jsp";
		SoccerDAO sDao = SoccerDAO.getInstance();
		SoccerVO sVo = new SoccerVO();
		sVo = sDao.memberCheck(id);
		
		if (sVo.getPw() == null) {
			// 아이디가 없음.
			request.setAttribute("message", "아이디가 없습니다.");
			request.getRequestDispatcher(url).forward(request, response);

		} else if (sVo.getPw().equals(pw)) {
			// 로그인 성공
			// 세션 생성
			// 회원정보 생성후 조회하기
			HttpSession session = request.getSession();
			session.setAttribute("sessionId", sVo);
			url = "Select.do?action=selectAll";

			if (request.getParameter("autoLogin") != null) {
				Cookie cookie = new Cookie("id", id);
				response.addCookie(cookie);
				response.addCookie(new Cookie("pw", pw));
				cookie.setMaxAge(60 * 60 * 12);
			}
			response.sendRedirect(url);
		} else {
			// 비밀번호가 틀림.
			request.setAttribute("message", "암호가 틀렸습니다.");
			request.getRequestDispatcher(url).forward(request, response);
		}

	}

}
