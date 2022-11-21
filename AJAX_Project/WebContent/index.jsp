<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX 배우기</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
	
	<h1>AJAX개요</h1>
	
	<p>
		
		Asynchronous JavaScript AND XML의 약자로 <br>
		서버로부터 데이터를 가져와 전체 페이지를 새로 고치지 않고
		일부만 로드해 내용물만 바꿀수 있는 기법 <br><br>
		
		참고로, 우리가 기존에 a태그를 이용해서 요청 및 form태그를 통해 요청한 방식은 동기식 요청 <br>
		=> 응답 페이지가 돌아와야 그 결과를 볼 수 있었다.(== 페이지 화면이 한번 깜빡거린다) <br><br>	
		
		비동기식 요청을 보내기 위해서는 AJAX라는 기술이 필요함 <br><br>
		
		* 동기식 / 비동기식 요청 차이 <br>
		- 동기식 : 요청 처리 후 해당하는 응답페이지가 돌아와야만 그 다음 작업이 가능 <br>
				 만약 서버에서 응답페이지를 돌려주는 시간이 지연되면 무작정 기다려야 함 <br>
				 전체 페이지가 리로드됨(새로고침, 즉 페이지가 기본적으로 한 번 깜빡거리면서 넘어감)<br><br>
		- 비동기식 : 현재 페이지는 그대로 유지하면서 중간중간마다 추가적인 요청을 보낼줄 수 있고<br>
				요청을 한다고 해서 다른페이지로 넘어가지 않음(현재 페이지 그대로 유지) <br>
				요청을 보내놓고 그에 해당하는 응답이 돌아올 떄 까지 현재 페이지에 다른 작업을 할 수 있음(페이지가 깜빡거리지 않음)
		예) NAVER 아이디 중복체크 기능, 댓글, 검색어 자동완성 등 <br><br>		
		
		* 비동기식의 단점 <br>
		- 페이지 내 복잡도가 기하급수적으로 증가 => 에러 발생 시 디버깅이 어려움 <br>
		- 요청 후 돌아온 응답데이터를 가지고 현재 페이지에서 새로운 요소를 동적으로 만들어서 뿌려줘야함
		=> DOM요소를 새롭게 만들어내는 구문을 잘 익혀둬야 함 <br><br>
		
		* AJAX 구현방식 : JavaScript방식/ jQuery방식 <br>
		=> jQuery가 코드가 간결하고 사용하기 쉽다. <br><br>
	</p>
	
	<pre>
		* jQuery에서의 AJAX통신
		
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
	</pre>
	
	<hr>
	
	<h1>jQuery 방식을 이용한 AJAX 테스트</h1>
	
	<h3>1. 버튼 클릭 시 get방식으로 서버에 데이터 전송 및 응답</h3>
	
	입력 : <input type="text" id="input1">
	<button id="btn1">전송</button>
	<br>
	
	응답 : <label id="output1">현재 응답없음</label>
	
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
	
	<hr>
	
	<h3>2. 버튼 클릭 시 POST방식으로 서버에 데이터 전송 및 응답</h3>
	이름: <input type="text" id="input2_1"><br>
	나이: <input type="number" id="input2_2"><br>
	<button onclick="test2();">요청</button>
	
	<br>
	
	응답 : <label id="output2">현재 응답 없음</label>
	
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
					console.log(result);
					/*
					$('#output2').text("이름 : " + result[0] + ", 나이" + result[1]);
					// 배열 형태로 넘겼을 때 가공한 것을 눈에 보이지게 하는 것은 부단의 역할(unboxing)
					*/
					
					// JSONObject로 넘겼을 경우
					// 자바스크립트 객체가 가지고 있는 속성값에 접근하는 두 가지 방법
					// 객체명['속성명']
					// 객체명.속성명
					$('#output2').text('이름 : ' + result.name + ', 나이 : ' + result.age);
					// input태그 값을 초기화 => val()에 접근후 '' 대입
					$('#input2_1').val('');
					$('#input2_2').val('');
				},
				error : function(){
					alert('AJAX통신 실패!');
				}
			});
		}
	
	</script>
	
	<hr>
	
	<h3>3. 서버로 데이터 전송 후, 조회된 객체를 응답데이터로 받기</h3>
	
	회원번호 입력 : <input type="text" id="input3">
	<button onclick="test3()">조회</button>
	
	<div id="output3"></div>
	
	<div>---- ArrayList로 받기 ----</div>
	<table id="output4">
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	</table>
	
	<script>
		function test3(){
			
			$.ajax({
				url : 'jqAjax3.do',
				data : {no : $('#input3').val()},
				success : function(result){
					
					console.log(result);
					// VO객체 하나만 보낸 케이스
					/*
					var resultStr = '회원번호 : ' 	+ result.userNo 	+ 	'<br>'
								  +	'회원이름 : ' 	+ result.userName 	+ 	'<br>'
								  +	'나이 : ' 	+ result.age + 			'<br>'
							      +	'성별 : ' 	+ result.gender;
					
					$('#output3').html(resultStr);
					*/
					
					// ArrayList로 VO를 여러 개 묶어서 보낸 케이스
					// => 반복문으로 문자열을 이어서 만들기(누적)
					
					var resultStr2 = '';
					
					for(var i = 0; i < result.length; i++){
						resultStr2 += '<tr>'
									+ '<td>' + result[i].userNo		+ '</td>'
									+ '<td>' + result[i].userName 	+ '</td>'
									+ '<td>' + result[i].age 		+ '</td>'
									+ '<td>' + result[i].gender 	+ '</td>'									
									+ '</tr>';
					}
					$('#output4 tbody').html(resultStr2);
				},
				error : function(){
					console.log("ajax요청실패")
				}
			});
		}
	</script>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>