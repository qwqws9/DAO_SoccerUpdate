package com.ejfrm.controller;

import java.io.IOException;
import java.util.ArrayList;

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

@WebServlet("/Select.do")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String url = "index.jsp";
		String action = request.getParameter("action");
		SoccerDAO sDao = SoccerDAO.getInstance();
		SoccerVO sVo = new SoccerVO();
		escape: if (action.equals("selectAll")) {

			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("id")) {
						sVo = sDao.memberCheck(c.getValue());
						for (Cookie d : cookies) {
							if (d.getName().equals("pw")) {
								if (sVo.getPw().equals(d.getValue())) {
									ArrayList<SoccerVO> list = new ArrayList<>();
									list = sDao.selectAll();
									request.setAttribute("list", list);
									HttpSession session = request.getSession();
									session.setAttribute("sessionId", sVo);
									url = "soccer/SelectTeam.jsp";
									break escape;
								}
							}
						}
					}
				}
			}

			HttpSession session = request.getSession();

			if (session.getAttribute("sessionId") == null) {
				url = "soccer/SelectTeam.jsp";
				request.setAttribute("message", "로그인후 사용해 주세요.");
			} else {
				ArrayList<SoccerVO> list = new ArrayList<>();
				list = sDao.selectAll();
				request.setAttribute("list", list);
				url = "soccer/SelectTeam.jsp";
			}

		} else if (action.equals("choice")) {
			url = "soccer/DeleteTeam.jsp";
			int code = Integer.parseInt(request.getParameter("code"));
			sVo = new SoccerVO();
			sVo = sDao.select(code);

			request.setAttribute("message", "구단 정보");
			request.setAttribute("sVo", sVo);
		}
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
