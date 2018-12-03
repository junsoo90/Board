<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<title>Insert title here</title>
</head>

<table class="table table-bordered">
	<tr>
		<th style="width: 10px">BNO</th>
		<th>TITLE</th>
		<th>WRITER</th>
		<th>REGDATE</th>
		<th style="width: 40px">VIEWCNT</th>
	</tr>
	<!-- for 문 -->

	 <c:forEach items="${list}" var="list">
		<!-- model의 속성 list에 담겨있는 조회결과를 차례로 boardVO에 대입-->
		<tr>
			<td>${list.bno}</td>
			<td><a href="/board/read?bno=${list.bno}">${list.title}</a></td>
			<td>${list.writer}</td>
			<td><fmt:formatDate pattern="YYYY-MM-dd HH:mm:ss"
					value="${list.regdate}" /></td>
			<td>${list.viewcnt}</td>
		</tr>
	</c:forEach>
</table>
<!-- 등록페이지로 가는 버튼 -->
<a href="/board/register"><button class="btn btn-primary">새글등록</button></a>

<script>
	var result = '${result}';
	$(function(){
		if(result === 'registerOK'){
			$('#registerOK').removeClass('hidden');
			$('#registerOK').fadeOut(2000);
		}
		if(result === 'removeOK'){
			$('#removeOK').removeClass('hidden');
			$('#removeOK').fadeOut(2000);
		}
	})
</script>
</html>