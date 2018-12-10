<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>
	//삭제 버튼 누르면 삭제할 것이냐고 묻고 삭제한다고 하면 주소이동(BoardController의 remove 메소드 호출)
	$(function() {
		$('#btn-remove').click(function() {
			if (confirm("Are u sure?")) {
				self.location.href = "/board/remove?bno=${boardVO.bno}";
			}
		});
	});
</script>
<div class="box-body">
	<span><b>글번호:</b> ${boardVO.bno}</span>

	<div class="form-group">
		<label for="title">Title</label> <input type="text" id="title"
			name="title" class="form-control" value="${boardVO.title}"
			readonly="readonly" />
	</div>

	<div class="form-group">
		<label for="content">Content</label>
		<textarea name="content" id="content" class="form-control" rows="3"
			readonly="readonly">${boardVO.content}</textarea>
	</div>

	<div class="form-group">
		<label for="writer">Writer</label> <input type="text" id="writer"
			name="writer" class="form-control" value="${boardVO.writer}"
			readonly="readonly" />
	</div>

</div>

<div>
	<!-- 목록 버튼 -->
	<a href="/board/listAll" class="btn btn-primary">LIST ALL</a>
	<!-- 수정 버튼 -->
	<a href="/board/update?bno=${boardVO.bno}" class="btn btn-warning">update</a>
	<!-- 삭제 버튼 -->
	<button id="btn-remove" class="btn btn-danger">delete</button>
</div>


</html>