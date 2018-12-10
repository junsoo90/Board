<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form role="form" method="POST" action="/board/test"
				enctype="multipart/form-data">
				<input type="hidden" id="bno" name="bno" value="${modify.bno }" />
				<input type="hidden" id="passwd" name="passwd"
					value="${modify.passwd }" />
				<p>
					<label for="title">글 제목</label> <input type="text" id="title"
						name="title" value="${modify.title}" />
				</p>
				<p>
					<label for="content">글 내용</label>
					<textarea id="content" name="content">${modify.content}</textarea>
				</p>
				<p>
					<label for="writer">작성자</label> <input type="text" id="writer"
						name="writer" value="${modify.writer}" readonly="readonly" /> <br />
				</p>
				<p>
					<label for="files1">첨부파일</label> 
						<a class="oldfile" id="oldfile" href="#">${modify.filename}</a> 
						<input type="button" id="filedelete" value="삭제" /> <br /> 
					<label for="files">파일첨부</label>
						<input type="file" id="files2" name="files2">
				</p>

				<p>
					<button type="submit" id="modify_btn">수정</button>
					<button type="button" id="cancel_btn">취소</button>
					<button type="button" id="list">목록</button>
				</p>

			</form>
</body>
</html>