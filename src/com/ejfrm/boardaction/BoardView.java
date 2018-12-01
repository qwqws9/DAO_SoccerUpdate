package com.ejfrm.boardaction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejfrm.DAO.SoccerDAO;
import com.ejfrm.VO.SoccerVO;

public class BoardView implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/board/boardView.jsp";
		String num = request.getParameter("num");
		
		SoccerDAO sDao = SoccerDAO.getInstance();
		SoccerVO sVo = new SoccerVO();
		sVo = sDao.selectOneBoard(num);
		sDao.hitUp(num);
		
		request.setAttribute("sVo", sVo);
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
