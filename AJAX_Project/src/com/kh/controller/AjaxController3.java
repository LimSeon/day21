package com.kh.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.model.vo.Member;

/**
 * Servlet implementation class AjaxController3
 */
@WebServlet("/jqAjax3.do")
public class AjaxController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxController3() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 1. GET => 인코딩 생략
		
		// 2. 값 뽑기
		int memberNo = Integer.parseInt(request.getParameter("no"));
		
		// 3. 가공처리
		
		// 4. 서비스단 호출
		
		// DB로부터 데이터를 조회했다는 가정하에 Member객체에 값을 담기
		Member m = new Member(memberNo, "고길동", 50, "남성");
		
		// m을 응답
		// 형식, 인코딩 지정
		// response.setContentType("text/html; charset=UTF-8");
		// response.getWriter().print(m);
		// 내부적으로 toString() 호출되어 문자열형태로 값이 넘어감
		
		// JSON
		// 자바객체 => 자바스크립트 객체로 변환(JSONObject)
		// JSONObject 객체 생성
		// JSONObject jObj = new JSONObject(); 	// {}
		/*
		jObj.put("userNo", m.getUserNo());		// {userNo : 1}
		jObj.put("userName", m.getUserName());	// {userNo : 1, userName : "고길동"}
		jObj.put("age", m.getAge());			// {userNo : 1, userName : "고길동", age : 50}
		jObj.put("gender", m.getGender());		// {userNo : 1, userName : "고길동", age : 50, gender : "남성"}
		
		// 응답으로 넘기기
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jobj);
		*/
		
		// 여러 개의 VO객체가 들어있는 ArrayList넘기기
		
		// ArrayList<Member>
		ArrayList<Member> list = new ArrayList();
		list.add(new Member(1, "고길동", 10, "남성"));
		list.add(new Member(2, "노길동", 20, "남성"));
		list.add(new Member(3, "도길동", 30, "여성"));
		
		// 객체 배열 
		/*
		// 객체를 담을 배열 생성
		JSONArray jArr = new JSONArray();				// []
		
		for(Member member : list) {
			// 배열에 담을 객체 생성
			JSONObject jObj = new JSONObject();
			jObj.put("userNo", member.getUserNo());		// {userNo : 1}
			jObj.put("userName", member.getUserName());	// {userNo : 1, userName : "고길동"}
			jObj.put("age", member.getAge());			// {userNo : 1, userName : "고길동", age : 50}
			jObj.put("gender", member.getGender());		// {userNo : 1, userName : "고길동", age : 50, gender : "남성"}
			
			jArr.add(member);
		}
		*/
		
		// GSON : Google JSON 라이브러리
		// GSON라이브러리 연동해야지만 사용가능
		// 무지성 최신버전 보다 내가 현재 개발하고 있는 환경과 호환성이 중요
		// 모르면 가장 많이 사용하는 것을 사용
		// https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.5에서 다운로드
		
		// 형식.인코딩 지정 => application/json;
		response.setContentType("application/json; charset=UTF-8");
		// GSON 객체 생성
		// Gson gson = new Gson();
		
		// Gson gson = new Gson();
		
		// gson.toJson() 호출
		// [ 표현법 ] gson.toJoin(응답할객체, 응답할스트림)
		// gson.toJson(m, response.getWriter());
		// => response.getWriter()라는 통로로 m이라는 객체를 응답하겠다!!
		// 내가 명시적으로 키값을 제시하지 않았기 때문에, 키값을 자동으로 필드명이 된다.
		
		// VO객체 하나만 응답 시 JSONObect {} 형태로 만들어져서 응답
		// ArrayList 응답 시 JSONArray [] 형태로 만들어져서 응답
		
		// Gson객체 생성 후 응답보내기
		new Gson().toJson(list, response.getWriter());
		// => list라는 변수를 response.getWriter()라는 통로로 보내겠다.
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
