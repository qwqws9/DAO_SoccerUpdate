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


@WebServlet("/Update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		int code = Integer.parseInt(request.getParameter("code"));
		SoccerDAO sDao = SoccerDAO.getInstance();
		SoccerVO sVo = new SoccerVO();
		sVo = sDao.select(code);
		
		request.setAttribute("sVo", sVo);
		
		RequestDispatcher disp = request.getRequestDispatcher("soccer/UpdateTeam.jsp");
		disp.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		ServletContext context = getServletContext();
		
		String path = context.getRealPath("upload");
		int sizeLimit = 5 * 1024 * 1024;
		String encType = "utf-8";
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
		
		SoccerVO sVo = new SoccerVO();
		sVo.setCode(Integer.parseInt(multi.getParameter("code")));
		sVo.setTeamname(multi.getParameter("teamname"));
		sVo.setCountry(multi.getParameter("country"));
		sVo.setHomeground(multi.getParameter("homeground"));
		sVo.setCoach(multi.getParameter("coach"));
		sVo.setPlayers(multi.getParameter("players"));
		sVo.setPicture(multi.getParameter("picture"));
		
		SoccerDAO sDao = SoccerDAO.getInstance();
		sDao.updateTeam(sVo);
		
		response.sendRedirect("Select.do?action=selectAll");
	}

}
