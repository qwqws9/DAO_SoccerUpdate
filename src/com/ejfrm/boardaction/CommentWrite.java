package com.ejfrm.boardaction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ejfrm.DAO.SoccerDAO;
import com.ejfrm.VO.SoccerVO;

public class CommentWrite implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SoccerVO sVo = new SoccerVO();
		HttpSession session = request.getSession();
		sVo = (SoccerVO) session.getAttribute("sessionId");
		
		sVo.setCid(sVo.getId());
		sVo.setCcontent(request.getParameter("c_content"));
		sVo.setParentnum(Integer.parseInt(request.getParameter("pnum")));
		
		
		SoccerDAO sDao = SoccerDAO.getInstance();
		sDao.hitDown(request.getParameter("pnum"));
		sDao.commentWrite(sVo);
		
		response.sendRedirect("BoardServlet?command=board_view&num="+ request.getParameter("pnum"));
	}

}
