package com.ejfrm.boardaction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejfrm.DAO.SoccerDAO;
import com.ejfrm.VO.SoccerVO;

public class BoardView implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "board/boardView.jsp";
		String num = request.getParameter("num");

		SoccerDAO sDao = SoccerDAO.getInstance();
		SoccerVO sVo = new SoccerVO();
		
		//게시글을 클릭했을때 보여줄 정보를 가지고옴
		sVo = sDao.selectOneBoard(num);
		sDao.hitUp(num);
		
		
		request.setAttribute("sVo", sVo);
		
		//댓글이 존재하는지 확인
		int result = sDao.commentCheck(num);
		if (result == 1) {
			//ArrayList<SoccerVO> list = sDao.commentView(num);
			
			//댓글 전체수를 조회하여 페이지숫자를 결정하는 코드
			int totalCount = sDao.commentTotalCount(num);
			
			int countPageView = totalCount/5;
			//댓글이 6개가 되었을때 다음페이지가 생겨야 하므로 나머지가 0보다 크면 페이지를 추가함
			if(totalCount % 5 > 0) {
				countPageView++;
			}
			//댓글이 5개 미만일때는 페이지를 보여주지않음
			if(totalCount > 5) {
			request.setAttribute("totalCount", countPageView);
			}
			//페이지를 매개변수로 던져야하기에 Null체크를 해서 페이지 변수에 값을 넣어줌
			int page = (request.getParameter("page") == null)? 1: Integer.parseInt(request.getParameter("page"));
			
			//게시글 번호와 페이지번호를 매개값으로 던져 댓글을 5개씩 긁어오는 코드
			ArrayList<SoccerVO> list = sDao.commentView(num, page);

			request.setAttribute("list", list);
			
		}else if(result == 0) {
			request.setAttribute("message", "첫 댓글의 주인공은 당신입니다.");
		}

		request.getRequestDispatcher(url).forward(request, response);

	}

}
