package com.ejfrm.idcontroller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ejfrm.DAO.SoccerDAO;
import com.ejfrm.VO.SoccerVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/UpdateUser.do")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String url = "member/Login.jsp";
		
		if(session.getAttribute("sessionId") == null) {
			request.setAttribute("message", "세션이 만료되었습니다. 다시 로그인 해주세요.");
			
		}else {
			/*
			 * 여기서 세션값이 있으면 url만 바꿔서 보내줬는데
			 * update.jsp 페이지에서 sessionId값으로 이미지 파일을 찾았더니
			 * 프로필사진을 바꾼뒤 정보수정을 다시 클릭하면 
			 * 로그인시 만들어졌던 세션값이라 프로필사진이 변경전으로 나오기에
			 * 아이디값을 파라미터로 받아서 정보 조회후 VO객체를 넘겨줌
			 */
			String id = request.getParameter("id");
			SoccerDAO sDao = SoccerDAO.getInstance();
			SoccerVO sVo = new SoccerVO();
			sVo = sDao.memberCheck(id);
			request.setAttribute("sVo", sVo);
			url = "member/updateUser.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		ServletContext context = getServletContext();
		String path = context.getRealPath("upload");
		int sizeLimit = 5 * 1024 * 1024;
		String enctype = "utf-8";
		
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, enctype, new DefaultFileRenamePolicy());
		
		SoccerDAO sDao = SoccerDAO.getInstance();
		SoccerVO sVo = new SoccerVO();
		sVo.setId(multi.getParameter("id"));
		sVo.setPw(multi.getParameter("pw"));
		sVo.setEmail(multi.getParameter("email"));
		sVo.setPhone(multi.getParameter("phone"));
		if(multi.getFilesystemName("profile") == null) {
			sDao.updateUser1(sVo);
		}else {
			sVo.setProfile(multi.getFilesystemName("profile"));
			sDao.updateUser(sVo);
		}
		response.sendRedirect("Select.do?action=selectAll");
	}

}
