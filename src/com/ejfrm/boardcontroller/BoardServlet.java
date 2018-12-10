package com.ejfrm.boardcontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.portable.ApplicationException;

import com.ejfrm.boardaction.Action;

@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		HttpSession session = request.getSession();
		if(session.getAttribute("sessionId") != null) {
		String command = request.getParameter("command");
		
		ActionControl ac = ActionControl.getInstance();
		Action action = ac.getAction(command);
		
		if(action != null) {
			action.execute(request, response);
		}
		}else {
			request.setAttribute("message", "세션이 만료되었습니다. 로그인을 다시 해주세요.");
			request.getRequestDispatcher("member/Login.jsp").forward(request, response);
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
