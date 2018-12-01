package com.ejfrm.boardaction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejfrm.DAO.SoccerDAO;

public class BoardDelete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SoccerDAO sDao = SoccerDAO.getInstance();
		
		String num = request.getParameter("num");
		
		sDao.deleteBoard(num);
		
		new BoardList().execute(request, response);
		
	}

	
}
