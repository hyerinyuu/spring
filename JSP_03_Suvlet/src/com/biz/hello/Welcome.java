package com.biz.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Welcome
 */
@WebServlet("/come") // URI값
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Welcome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 지금부터 browser로 전송되는 데이터는 
		// text의 html(일종의 도큐 type) 문서이다.
		// 한글은 utf-8 방식으로 encoding해서 취급하라
		response.setContentType("text/html;charset=UTF-8");
		
		// response객체는 Server에서 WebBrowse로 연결되는 통로와 정보를 가지고 있다.
		// response로부터 writer객체를 요청을 하는데
		// 이 writer객체는 서버에서 어던 데이터를 webBrowser로 보내는 통로로 사용된다.
		PrintWriter wOut = response.getWriter();
		// html태그를 자바에서 만드는 것
		
		wOut.println("<body>");
		wOut.println("<h3>우리집에 오신 것을 환영합니다.</h3>");
		wOut.println("<p>나는 survlet에서 보낸 메시지 입니다.</p>");  // close를 해줘야하니까 </p> 닫아주기
		wOut.println("</body>");
		wOut.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
