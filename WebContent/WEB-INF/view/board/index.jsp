<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" Content="text/html; charset=utf-8" />


<html>
<body>
	<a href="/board/write">글 작성</a>
	<br />
	<a href="/board/list">글 목록</a>
	<br />
	<a href="/board/listPage">글 목록 + 페이지</a>
	<br />
	<a href="/board/listSearch">글 목록 + 페이지 + 목록</a>
	<br />
	<a href="/board/test">테스트</a>

	<input type="text" id="text" name="text" />

	<button type="button" onClick="check()">버튼</button>

	<script type=text/javascript>

		var str = document.getElementById("text");
			function check() {

				if (!text.value) {
					alert("제목를 입력해주세요");
					text.focus();
					return false;
				} 
				else
					byteCheck(str)
			}
			function byteCheck(obj){

				//var obj = document.getElementById("text");
				
			    var maxByte = 10; //최대 입력 바이트 수
			    
			    var str = obj.value;
			    var str_len = str.length;
			 
			    var rbyte = 0;
			    var rlen = 0;
			    var one_char = "";
			    var str2 = "";

			    for (var i = 0; i < str_len; i++) {
			        one_char = str.charAt(i);
			 
			        if (escape(one_char).length > 4) {
			            rbyte += 2; //한글2Byte
			        } else {
			            rbyte++; //영문 등 나머지 1Byte
			        }
			 
			        if (rbyte <= maxByte) {
			            rlen = i + 1; //return할 문자열 갯수
			        }
			    }
			    
			    if (rbyte > maxByte) {
			        alert("초과 입력할 수 없습니다.");
			        obj.value = 0;
			    } 
			}
	</script>
	<!-- 
	
	
	<c:if test="${member == null}">
		<form role="form" method="post" autocomplete="off"
			action="/member/login">
			<p>
				<label for="userId">아이디</label> <input type="text" id="userId"
					name="userId" />
			</p>
			<p>
				<label for="userPass">패스워드</label> <input type="password"
					id="userPass" name="userPass" />
			</p>
			
	<p><button type="submit">로그인</button></p>
	<p><a href="/member/register">회원가입</a></p>
	 
		</form>
	</c:if>
	
<c:if test="${msg == false}">
	<p style="color:#f00;">로그인에 실패했습니다. 아이디 또는 패스워드를 다시 입력해주십시오.</p>
</c:if>

<c:if test="${member != null}">
	<p>${member.userName}님 환영합니다.</p>
	
	<a href="member/modify">회원정보 수정</a>, <a href="member/withdrawal">회원탈퇴</a><br />
	<a href="member/logout">로그아웃</a>
	
</c:if>
-->
</body>
</html>











