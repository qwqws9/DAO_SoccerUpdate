package com.ejfrm.boardaction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ejfrm.DAO.SoccerDAO;
import com.ejfrm.VO.SoccerVO;

public class BoardWrite implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String command = request.getParameter("command");

		if (command.equals("board_write_form")) {

			request.getRequestDispatcher("board/boardWrite.jsp").forward(request, response);
			
		} else if (command.equals("board_write")) {
			
			SoccerDAO sDao = SoccerDAO.getInstance();

			HttpSession session = request.getSession();
			SoccerVO sVo = (SoccerVO) session.getAttribute("sessionId");
			String id = sVo.getId();

			sVo = new SoccerVO();
			sVo.setMid(id);
			sVo.setPass(request.getParameter("pass"));
			sVo.setTitle(request.getParameter("title"));
			sVo.setContent(request.getParameter("content"));

			sDao.writeBoard(sVo);

			new BoardList().execute(request, response);
		}

	}

}
