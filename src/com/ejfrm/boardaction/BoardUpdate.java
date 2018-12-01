package com.ejfrm.boardaction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejfrm.DAO.SoccerDAO;
import com.ejfrm.VO.SoccerVO;

public class BoardUpdate implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String command = request.getParameter("command");
		String num = request.getParameter("num");
		SoccerVO sVo = new SoccerVO();
		SoccerDAO sDao = SoccerDAO.getInstance();
		String url = null;
		if(command.equals("board_update_form")) {
			
			url = "board/boardUpdate.jsp";
			
		
			sVo = sDao.selectOneBoard(num);
			
			request.setAttribute("sVo", sVo);
			
			request.getRequestDispatcher(url).forward(request, response);
			
			
		}else if (command.equals("board_update")) {
			
			sVo.setNum(Integer.parseInt(request.getParameter("num")));
			sVo.setTitle(request.getParameter("title"));
			sVo.setContent(request.getParameter("content"));
			sVo.setPass(request.getParameter("pass"));
			sDao.updateBoard(sVo);
			
			new BoardList().execute(request, response);
			
		}
		
		
		
		
	}

	
}
