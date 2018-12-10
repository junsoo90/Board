<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>kuzuro 게시판</title>


<!-- 제이쿼리 -->
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>

</head>
<body>

	<div id="root">

		<section id="container">
			<form role="form" method="post" autocomplete="off">
				
				${writer}님 비밀번호를 입력해주세요.
				<p>
				비밀번호 : 
					<input type="text" id="inputpasswd" name="inputpasswd"/>
					
					<button type="submit">확인</button>
					<button type="button" id="cancel_btn">취소</button>
					<script>
						// 폼을 변수에 저장
						var formObj = $("form[role='form']");
					
						// 취소 버튼 클릭
						$("#cancel_btn").click(
								function() {

									self.location = "/board/read?bno=${no}"
											+ "&page=${scri.page}"
											+ "&perPageNum=${scri.perPageNum}"
											+ "&searchType=${scri.searchType}"
											+ "&keyword=${scri.keyword}";
								});
					</script>
				</p>
			</form>
		</section>

	</div>
</body>
</html>








