package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxController1
 */
@WebServlet("/jqAjax1.do")
public class AjaxController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxController1() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) GET방식 => 인코딩 생략
		
		// 2) 값 뽑기
		String str = request.getParameter("input");
		
		System.out.println("요청 시 전달값 :" + str);
		
		System.out.println("입력된 값 : " + str + ", 길이 : "+ str.length());
		
		// 응답
		
		// 1) 혹시라도 응답데이터에 한글이 있을 경우를 대비해서
		// 항상 응답데이터에 대해서 인코딩 설정
		response.setContentType("text/html; charset=UTF-8");
		
		// 2) 응답
		// => jsp와의 통로를 열어준다.(자바 코드안에 html코드를 넣을 때 썼음)
		response.getWriter().print("입력된 값 : " + str + ", 길이 : "+ str.length());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
