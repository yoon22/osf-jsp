package com.osf.test.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osf.test.service.CommonCodeService;
import com.osf.test.service.TransService;
import com.osf.test.service.impl.CommonCodeServiceImpl;
import com.osf.test.service.impl.TransServiceImpl;


@WebServlet("/TransServlet")
public class TransServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommonCodeService ccs = new CommonCodeServiceImpl();
	private TransService ts = new TransServiceImpl();
	//리퀘스트에 담아서 doget을 호출할거야 ! ! ! !


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		RequestDispatcher rd = request.getRequestDispatcher("/views/transper/trans.jsp");
		request.setAttribute("ccList", ccs.selectCommonCodeList("trans"));
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		request.setCharacterEncoding("utf-8");
		String source = request.getParameter("source");
		String target = request.getParameter("target");
		String text = request.getParameter("text");
		request.setAttribute("rMap", ts.transperText(source, target, text));
		doGet(request,response);
		
		
	}

}
