package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class AjaxController2
 */
@WebServlet("/jqAjax2.do")
public class AjaxController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxController2() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. POST => 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// System.out.println("요청 test");
		
		// 2. 값 뽑기
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		// 3. VO가공 => 생략
		
		// 4. Service 요청 생략
		
		// 5. 결과에 따른 응답
		/*
		// 한글이 있을 경우를 대비해서 인코딩 설정(필수)
		response.setContentType("text/html; charset=UTF-8");
		
		// 데이터 2개 넘기기 => 못넘김
		response.getWriter().print(name, age);
		// => ajax는 결과를 오로지 한개만 응답할 수 있음
		*/
		
		// 어쩔수없이
		
		// 방법 1) 하나의 데이터로 만들어서 보내기
		// 이름 : XXX, 나이 : XX
		/*
		String responseData = "이름 : " + name + ", 나이: " + age;
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(responseData);
		*/
		
		// 방법 2) AJAX로 실제 값을 여러 개 보내고 싶을 때 => 정석
		// => JSON(JavaScript Object Notation : 자바스크립트 객체 표기법)
		// AJAX통신 시 데이터 전송에 이용되는 포맷형식 중 하나
		// [value, value, value, ...]		=> 자바스크립트에서의 배열 객체 => JSONArray
		// {key:value, key:value...} 	  	=> 자바스크립트에서의 일반 객체 => JSONObject
		
		/*
		 * JSON처리 시 사용하는 클래스 종류
		 * => 자바에서 기본적으로 제공X(라이브러리 필요.jar)
		 * 
		 * https://code.google.com/archive/p/json-simple/downloads 접속 후 다운로드
		 * json-simple-1.1.1.jar 다운로드  후 WEB-INF\lib에 붙여넣기 
		 * 
		 * 
		 * 1. JSONArray => [값1, 값2...] 배열 형태로 값을 넘길 수 있음
		 * 2. JSONObject => {키1: 벨류1, 키2 : 벨류2} 객체 형태로 값을 넘길 수 있음
		 * 
		 */
		
		JSONArray jArr = new JSONArray(); // [] 빈 배열이 만들어짐(자바스크립트)
		// 배열에 요소 추가 => add
		jArr.add(name); // ['홍길동']
		jArr.add(age);  // ['홍길동', 15]
		
		/*
		// 인코딩
		// response.setContentType("text/html; charset=UTF-8");
		// 배열이 아니라 문자열로 전송됨
		// => text/html로 넘기게 되면 문자열로 "["홍길동", 15]"이 전달됨
		// 응답할 데이터의 컨텐트타입을 정해야 문자열 형식으로 넘어가지 않는다.
		response.setContentType("application/json; charset=UTF-8");
		
		// 보내기
		response.getWriter().print(jArr);
		*/
		
		// 마찬가지로 JSONObject로도 넘기기가 가능
		JSONObject jobj = new JSONObject();	// {} 빈객체(자바스크립트가)가 만들어짐
		// 객체에 값 담기 => put메소드
		jobj.put("name", name);		// {name : "홍길동"}
		jobj.put("age", age); 		// {name : "홍길동", age : 15}
		
		// JSON타입의 값이라고 컨텐트 타입, 인코딩 지정
		
		response.setContentType("application/json; charset=UTF-8");
		
		// 보내기
		response.getWriter().print(jobj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
