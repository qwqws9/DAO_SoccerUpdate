package com.ejfrm.boardaction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejfrm.DAO.SoccerDAO;
import com.ejfrm.VO.SoccerVO;

public class BoardDelete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SoccerDAO sDao = SoccerDAO.getInstance();
		
		String num = request.getParameter("num");
		SoccerVO sVo = new SoccerVO();
		sVo = sDao.selectOneBoard(num);
		int pnum = sVo.getRpnum();
		int step = sVo.getRstep();
		int indent = sVo.getRindent();
		
		sDao.deleteBoard(num,pnum,step,indent);
		
		new BoardList().execute(request, response);
		
	}

	
}
