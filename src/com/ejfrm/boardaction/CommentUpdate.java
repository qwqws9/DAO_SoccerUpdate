package com.ejfrm.boardaction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejfrm.DAO.SoccerDAO;
import com.ejfrm.VO.SoccerVO;

public class CommentUpdate implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "board/commentChoice.jsp";
		
		String command = request.getParameter("command");
		String cnum = request.getParameter("cnum");
		
		if(command.equals("comment_edit_form")) {
			
			url = "board/commentUpdate.jsp";
			SoccerDAO sDao = SoccerDAO.getInstance();
			SoccerVO sVo = new SoccerVO();
			sVo = sDao.selectComment(cnum);
			
			request.setAttribute("sVo", sVo);
			
			
		}else if (command.equals("comment_edit")) {
			
			String ccontent = request.getParameter("c_content");
			
			SoccerDAO sDao = SoccerDAO.getInstance();
			sDao.commentUpdate(cnum, ccontent);
			SoccerVO sVo = new SoccerVO();
			sVo = sDao.selectComment(cnum);
			request.setAttribute("sVo", sVo);
			
			
			url = "board/commentOk.jsp";
			
			
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
		
		
	}

	
	
}
