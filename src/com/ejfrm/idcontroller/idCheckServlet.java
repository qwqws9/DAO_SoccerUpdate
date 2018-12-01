package com.ejfrm.idcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejfrm.DAO.SoccerDAO;


@WebServlet("/idCheck.do")
public class idCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "member/idCheck.jsp";
		String action = request.getParameter("action");
		if (action.equals("check")) {
			int result = -1;
			String id = request.getParameter("id");
			SoccerDAO sDao = SoccerDAO.getInstance();
			result = sDao.idCheck(id);
			request.setAttribute("result", result);
			request.setAttribute("id", id);
			
		} else if (action.equals("admin")) {
			url = "member/adminCheck.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pw = request.getParameter("pw");
		System.out.println(pw);
		SoccerDAO sDao = SoccerDAO.getInstance();
		int result = sDao.adminCheck(pw);
		System.out.println(result);
		if (result == 1) {
			request.setAttribute("ok",1 );
		}else if (result == -1) {
			request.setAttribute("ok",-1);
		}
		RequestDispatcher disp = request.getRequestDispatcher("member/adminCheck.jsp");
		disp.forward(request, response);
	}

}
