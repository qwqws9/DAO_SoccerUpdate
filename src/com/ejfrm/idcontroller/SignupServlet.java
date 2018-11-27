package com.ejfrm.idcontroller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejfrm.DAO.SoccerDAO;
import com.ejfrm.VO.SoccerVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/Signup.do")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("upload");
		String encType = "utf-8";
		int sizeLimit = 5 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
		
		SoccerVO sVo = new SoccerVO();
		sVo.setId(multi.getParameter("id"));
		sVo.setPw(multi.getParameter("pw"));
		sVo.setEmail(multi.getParameter("email"));
		sVo.setPhone(multi.getParameter("phone"));
		sVo.setProfile(multi.getFilesystemName("profile"));
		sVo.setGrade(Integer.parseInt(multi.getParameter("grade")));
		
		SoccerDAO sDao = SoccerDAO.getInstance();
		sDao.signupUser(sVo);
		
		request.setAttribute("id", sVo.getId());
		
		request.getRequestDispatcher("member/Login.jsp")
		.forward(request, response);
	}

}
