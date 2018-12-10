package com.ejfrm.boardaction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejfrm.DAO.SoccerDAO;
import com.ejfrm.VO.SoccerVO;

public class CommentDelete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cnum = request.getParameter("cnum");
		
		SoccerDAO sDao = SoccerDAO.getInstance();
		
		SoccerVO sVo = new SoccerVO();
		sVo = sDao.selectComment(cnum);
		sDao.commentDelete(cnum);
		request.setAttribute("sVo", sVo);
		
		request.getRequestDispatcher("board/commentOk.jsp").forward(request, response);
		
		
	}

}
