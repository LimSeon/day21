# day21
AJAX 수업내용

## AJAX개요
    
            Asynchronous JavaScript AND XML의 약자로
		서버로부터 데이터를 가져와 전체 페이지를 새로 고치지 않고
		일부만 로드해 내용물만 바꿀수 있는 기법 
		참고로, 우리가 기존에 a태그를 이용해서 요청 및 form태그를 통해 요청한 방식은 동기식 요청
		=> 응답 페이지가 돌아와야 그 결과를 볼 수 있었다.(== 페이지 화면이 한번 깜빡거린다)	
		비동기식 요청을 보내기 위해서는 AJAX라는 기술이 필요함 
		* 동기식 / 비동기식 요청 차이
		- 동기식 : 요청 처리 후 해당하는 응답페이지가 돌아와야만 그 다음 작업이 가능
				 만약 서버에서 응답페이지를 돌려주는 시간이 지연되면 무작정 기다려야 함 
				 전체 페이지가 리로드됨(새로고침, 즉 페이지가 기본적으로 한 번 깜빡거리면서 넘어감)
		- 비동기식 : 현재 페이지는 그대로 유지하면서 중간중간마다 추가적인 요청을 보낼줄 수 있고
				요청을 한다고 해서 다른페이지로 넘어가지 않음(현재 페이지 그대로 유지)
				요청을 보내놓고 그에 해당하는 응답이 돌아올 떄 까지 현재 페이지에 다른 작업을 할 수 있음(페이지가 깜빡거리지 않음)
		예) NAVER 아이디 중복체크 기능, 댓글, 검색어 자동완성 등 
		* 비동기식의 단점 
		- 페이지 내 복잡도가 기하급수적으로 증가 => 에러 발생 시 디버깅이 어려움 
		- 요청 후 돌아온 응답데이터를 가지고 현재 페이지에서 새로운 요소를 동적으로 만들어서 뿌려줘야함
		=> DOM요소를 새롭게 만들어내는 구문을 잘 익혀둬야 함
		* AJAX 구현방식 : JavaScript방식/ jQuery방식 
		=> jQuery가 코드가 간결하고 사용하기 쉽다.

## jQuery에서의 AJAX통신
	[ 표현법 ]
		$.ajax({
			속성명 : 속성값,
			속성명 : 속성값,
			속성명 : 속성값,
			...
		});
		=> AJAX기술이 가능하게 하는 객체를 인자값으로 넘긴다고 생각하자.
		
		* 주요속성
		- url : 요청할url(필수로 작성) => form태그의 action속성
		- type/method : 요청 전송방식(get/post 생략 시 기본값은 get) => form태그의 method속성
		- data : 요청 시 전달할 값({키:밸류, 키:밸류, 키:밸류, ...}) => form태그 안에 input태그에 입력한 값
		- success : AJAX 통신 성공 시 실행할 함수를 정의
		- error : AJAX 통신 실패 시 실행할 함수를 정의
		- complete : AJAX통신을 성공이든 실패든 간에 무조건 끝나면 실행할 함수를 정의
		
		* 부수적인 속성
		- dataType : 서버에서 response로 오는 데이터의 데이터 형 설정, 값이 없다면 스마트하게 판단함
    
## jQuery 방식을 이용한 AJAX 테스트
1. 버튼 클릭 시 get방식으로 서버에 데이터 전송 및 응답


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
      
    <hr>
    
      	<script>
		$(function(){
			
			$('#btn1').click(function(){
				
 				// 동기식 통신 : location.href = '요청url?쿼리스트링'
 						
 				// 비동기식 통신 :
 				$.ajax({
 					url : 'jqAjax1.do',
 					data : {input : $('#input1').val()},
 					type : 'get',
 					// 요청애 대한 응답을 success 속성에서 실행할 함수의 매개변수를 통해 받아준다.
 					success : function(result){
 						// console.log(result);
 						$('#output1').text(result);
 					},
 					error : function(){
 						alert('ajax통신 실패!');
 					},
 					complete : function(){
 						console.log('실패하든 성공하든 무조건 나오는 콘솔');
 					}
 				});
			})
		})	
        </script>


2. 버튼 클릭 시 POST방식으로 서버에 데이터 전송 및 응답

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
       
    <hr>
        
        <script>
        function test2(){
          $.ajax({
            url : 'jqAjax2.do',
            data : {
              name : $('#input2_1').val(),
              age : $('#input2_2').val()
            },
            type : 'post',
            success : function(result){
              // console.log(result);
              $('#output2').text(result);
            },
            error : function(){
              alert('AJAX통신 실패!');
            }
          });
        }
        </script>


  + AJAX로 실제 값을 여러 개 보내고 싶을 때 <br>
  
		 => JSON(JavaScript Object Notation : 자바스크립트 객체 표기법)
		 AJAX통신 시 데이터 전송에 이용되는 포맷형식 중 하나
		 [value, value, value, ...]		=> 자바스크립트에서의 배열 객체 => JSONArray
		 {key:value, key:value...} 	  	=> 자바스크립트에서의 일반 객체 => JSONObject
		
		 JSON처리 시 사용하는 클래스 종류
		 => 자바에서 기본적으로 제공X(라이브러리 필요.jar)
		 
		 https://code.google.com/archive/p/json-simple/downloads 접속 후 다운로드
		 json-simple-1.1.1.jar 다운로드  후 WEB-INF\lib에 붙여넣기 
		 
     JSONArray => [값1, 값2...] 배열 형태로 값을 넘길 수 있음 <br>
     JSONObject => {키1: 벨류1, 키2 : 벨류2} 객체 형태로 값을 넘길 수 있음 
     
     
    
3. 서버로 데이터 전송 후, 조회된 객체를 응답데이터로 받기

	      GSON : Google JSON 라이브러리

	      Gson gson = new Gson();

	      [ 표현법 ]
	      gson.toJoin(응답할객체, 응답할스트림)
	      gson.toJson(m, response.getWriter());
	      => response.getWriter()라는 통로로 m이라는 객체를 응답하겠다.
	      => 명시적으로 키 값을 제시하지 않으면, 키 값은 자동으로 필드명이 된다.

