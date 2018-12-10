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

		//page Null 체크 
		int page = (request.getParameter("page") == null)? 1 : Integer.parseInt(request.getParameter("page"));
		
		SoccerDAO sDao = SoccerDAO.getInstance();
		
		//전체 게시물 가져오기
		ArrayList<SoccerVO> list = new ArrayList<>();
		list = sDao.selectTest(page);
		//list = sDao.selectAllboard();
		
		//게시물별 댓글수를 조회하여 제목 옆에 [댓글수] 를 표시하는 부분
		ArrayList<SoccerVO> clist = new ArrayList<>();
		clist = sDao.commentCount();
		
		request.setAttribute("clist", clist);
		request.setAttribute("list", list);
		
		String url = "board/boardList.jsp";
		
		//게시글의 개수를 가져옴
		int totalCount = sDao.selectTotalCount();
		
		int countPageView = totalCount/10;
		if(totalCount % 10 > 0) {
			countPageView++;
		}
		//게시글을 10개 미만일때는 보여주지않음
		if(totalCount >10) {
		request.setAttribute("countPageView", countPageView);
		}
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
