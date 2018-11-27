package com.ejfrm.idcontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ejfrm.DAO.SoccerDAO;
import com.ejfrm.VO.SoccerVO;

@WebServlet("/DeleteUser.do")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String url = "member/Login.jsp";

		if (session.getAttribute("sessionId") == null) {

			request.setAttribute("message", "세션이 만료되었습니다.");

		} else {
			url = "member/deleteUser.jsp";

		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SoccerVO sVo = new SoccerVO();
		HttpSession session = request.getSession();

		sVo = (SoccerVO) session.getAttribute("sessionId");
		SoccerDAO sDao = SoccerDAO.getInstance();
		sDao.deleteUser(sVo.getId());

		request.setAttribute("message", "회원탈퇴가 되었습니다.");
		session.invalidate();

		request.getRequestDispatcher("member/Login.jsp").forward(request, response);

	}
}
