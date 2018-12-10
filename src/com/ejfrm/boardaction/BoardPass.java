package com.ejfrm.boardaction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejfrm.DAO.SoccerDAO;
import com.ejfrm.VO.SoccerVO;

public class BoardPass implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String command = request.getParameter("command");

		if (command.equals("board_pass")) {
			request.getRequestDispatcher("board/boardPass.jsp").forward(request, response);
			
		} else {
			String num = request.getParameter("num");
			String pass = request.getParameter("pass");
			String url = null;
			SoccerDAO sDao = SoccerDAO.getInstance();
			SoccerVO sVo = new SoccerVO();
			sVo = sDao.selectOneBoard(num);
			if (sVo.getPass().equals(pass)) {
				url = "board/closeWindow.jsp";
			} else {
				url = "board/boardPass.jsp";
				request.setAttribute("message", "비밀번호가 틀렸습니다");
			}
			request.getRequestDispatcher(url).forward(request, response);
		}

	}

}
