package com.ejfrm.boardaction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejfrm.DAO.SoccerDAO;
import com.ejfrm.VO.SoccerVO;

public class BoardList implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SoccerDAO sDao = SoccerDAO.getInstance();
		ArrayList<SoccerVO> list = new ArrayList<>();
		list = sDao.selectAllboard();
		
		request.setAttribute("list", list);
		
		String url = "board/boardList.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
