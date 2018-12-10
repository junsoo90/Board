<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
			<form role="form" method="post" action="/board/passwdResult"
			enctype="multipart/form-data">
					${vo.title }, ${vo.content }, ${vo.bno }, ${vo.passwd }
					<input type="hidden" id="title" name = "title" value="${vo.title }" /> 
					<input type="hidden" id="content" name="content" value="${vo.content }" />
					<input type="hidden" id="bno" name="bno" value="${vo.bno }" />
					<input type="hidden" id="oldpasswd" name="oldpasswd" value="${vo.passwd }" />
					<input type="hidden" id="files" name="files" value="${files}" style = "display:none">
					
					 
					${vo.writer}님 비밀번호를 입력해주세요.
					
				<p>
					비밀번호 : <input type="text" id="passwd" name="passwd" />
					
					
					<button type="submit" id="ok">확인</button>
					<button type="button" id="cancel_btn">취소</button>
				</p>
			</form>
		</section>

		<script type="text/javascript">
	
	
		</script>
	</div>
</body>
</html>








