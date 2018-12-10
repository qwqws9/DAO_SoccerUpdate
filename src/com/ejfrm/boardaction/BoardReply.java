package com.ejfrm.boardaction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ejfrm.DAO.SoccerDAO;
import com.ejfrm.VO.SoccerVO;

public class BoardReply implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "board/replyForm.jsp";
		String num = request.getParameter("num");
		SoccerDAO sDao = SoccerDAO.getInstance();
		SoccerVO sVo = new SoccerVO();
		
		
		if(request.getParameter("command").equals("board_reply_form")) {
			
			 sVo = sDao.selectOneBoard(num);
			
			 //게시글 번호로 제목 가져옴
			request.setAttribute("title", sVo.getTitle());
			
			
			
		}else if (request.getParameter("command").equals("board_reply")) {
			
			//로그인한 유저의 아이디를 세션정보에서 가져옴
			HttpSession session = request.getSession();
			sVo  = (SoccerVO)session.getAttribute("sessionId");
			
			String id = sVo.getId();
			
			//게시글 번호로 게시글 정보를 가져와서 인덴트와 스텝 체크
			sVo = sDao.selectOneBoard(num);
			int indent = sVo.getRindent();
			int step = sVo.getRstep();
			int pnum = sVo.getRpnum();
			//부모글에 답글 작성시
			if(pnum == 0) {
				step += 1;
				++indent;
				sVo.setRpnum(Integer.parseInt(num));
			//답글의 답글일 경우
			}else if (pnum != 0) {
				indent += 1;
				
				sVo.setRpnum(sVo.getRpnum());
			}
			
			sVo.setRid(id);
			sVo.setRtitle(request.getParameter("r_title"));
			sVo.setRpass(request.getParameter("r_pass"));
			sVo.setRcontent(request.getParameter("r_content"));
			sVo.setRindent(indent);
			sVo.setRstep(step);
			
			sDao.replyInsert(sVo);
			
			url = "board/closeWindow.jsp";
			//new BoardList().execute(request, response);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
