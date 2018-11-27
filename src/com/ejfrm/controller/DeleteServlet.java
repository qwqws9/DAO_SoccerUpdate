package com.ejfrm.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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


@WebServlet("/Delete.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int code = Integer.parseInt(request.getParameter("code"));
		
		SoccerDAO sDao = SoccerDAO.getInstance();
		SoccerVO sVo = new SoccerVO();
		sVo = sDao.select(code);
		
		request.setAttribute("message", "구단 삭제");
		request.setAttribute("sVo", sVo);
		request.setAttribute("choice", "삭제");
		
		RequestDispatcher disp = request.getRequestDispatcher("soccer/DeleteTeam.jsp");
		disp.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("upload");
		int sizeLimit = 5 * 1024 * 1024;
		String encType = "utf-8";
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
		
		int code = Integer.parseInt(multi.getParameter("code"));
		System.out.println(code);
		SoccerDAO sDao = SoccerDAO.getInstance();
		sDao.DeleteTeam(code);
		
		response.sendRedirect("Select.do?action=selectAll");
	}

}
