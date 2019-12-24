package com.biz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpServlet클래스를 상속받아서 생성한 servlet Class
 * 사용자가 웹을 통해서 request를 보내면
 * 요청을 수신해서 처리할 App의 대문 역할을 하는 class
 * console환경에서 main() method가 있는 Class와 비슷한 역할을 수행
 * 
 * 1. 사용자가 web browser를 통해 request를 보내면
 * 2. 서버 컴퓨터의 네트워크를 통해서 전송된 데이터를 TomCat이 수신
 * 3. URL의 context부분을 확인하여 현재 실행되고 있는 프로젝트가 있는지 확인
 * 4. 프로젝트에 작성되어 있는 Servlet클래스들이 있는지 확인
 * 5. servlet의 //@WebServlet()항목에 설정된 path를 scan하여
 * 5. 사용자의 요청을 해당 클래스의 doGet(), doPost() method로 전달을 수행
 * 
 *  tomcat은 APP이 Server에 의해 실행되면(Run as Server)
 *  1. 프로젝트에 담겨있는 모든 *.jsp 파일을 내부적으로 다시 *_jsp.java(내부적으로 servlet과 구조가 동일)로
 *     언어 컴파일(translation)을 수행하고
 *  2. *_jsp.java를 *_jsp.class파일로 컴파일을 수행
 *  3. 이 *_jsp.class들을 container에 리스트로 올려두고 request를 대기
 *  
 *  // 사용자가 생성한 servlet, tomcat이 생성한 _jsp.class모두
 *  4. servlet클래스가 있으면 이 클래스들도 모두 컴파일을 수행하고
 *     객체로 선언, 생성하여 container에 리스트로 올려두고
 *  
 *  5. web에 request가 오면 이 contatiner리스트에서 해당 정보를 찾아서 사용자의 요청을 처리
 *  
 *  //@WebServlet()
 *  사용자의 request URL(URI)중에 path라고 하는 부분의 ID값을 설정하는 부분
 *  WAS(Web App Service, Web App Server) 환경에서는 
 *  클래스 이름은 외부로 직접 노출되지 않고,
 *  path에 지정된 값을 통해서 외부 접근을 허용한다.
 *  
 *  path는 모두 소문자로 쓰는 것이 사용자의 혼란을 막는다.
 *  
 *  id값은 현재 프로젝트에서 유일한 값이어야 한다.
 *  경우에 따라서 /main_path/sub_path/child_path형식으로 path를 다중 설정할수도 있다.
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// jasp의 input box(strName)에 담겨온 문자열을 추출하여
		// strName변수에 담기
		String strName = request.getParameter("strName");
		String strDept = request.getParameter("strDept");
		String strGrade = request.getParameter("strGrade");
		
		// console추출 부분
		System.out.println(strName);
		System.out.println(strDept);
		System.out.println(strGrade);
		
		
		// web에 문자열을 보낼 때 한글(영어이외) 문자 encoding
		response.setContentType("text/html;charset=UTF-8");
		
		// server에서 web 으로 데이터를 return하는 통로를 오픈하는 역할을 수행
		// PrintWriter wOut = response.getWriter();
		// wOut.println(strName);
		// wOut.close();
	
		// web화면에 어떤 데이터를 표시하는 것이 아니고
		// webcontent/hellp.jsp파일을 열어서 web으로 전송하라
		// jsp파일을 디자이너가 design, .java는 개발자가 관리 - 업무 분리
		String sendString = "?strName=" + URLEncoder.encode(strName, "UTF-8");
		sendString += "&strDept=" + URLEncoder.encode(strDept, "UTF-8");
		sendString += "&strGrade=" + URLEncoder.encode(strGrade, "UTF-8");
		response.sendRedirect("/JSP_04_Servlet/view.jsp" + sendString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
