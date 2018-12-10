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

			<form role="form" enctype="multipart/form-data" method="post"
				action="/board/passwdCheck" onsubmit="return check();">

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
					<label>비밀번호</label> <input type="text" id="inputpasswd"
						name="inputpasswd" /> <br />
				</p>

				<p>
					<label for="files1">첨부파일</label> <a class="oldfile" id="oldfile"
						name="oldfile" href="#">${modify.fileOriname}</a> 
						<input
						type="button" id="filedelete" name = "filedelete" value="삭제" /> 
						<br /> <label
						for="files">파일첨부</label> <input type="file" id="files"
						name="files">
				</p>

				<p>
					<button type="submit" id="modify_btn">수정</button>
					<button type="button" id="cancel_btn">취소</button>
					<button type="button" id="list">목록</button>
				</p>
			</form>
		</section>

		<script type=text/javascript>

			var formObj = $("form[role='form']");
			var oldfile = document.getElementById("oldfile");
			var inputpasswd = document.getElementById("passwd");
			
			$("#filedelete").click(function() {
				self.location="/board/fileDeleteCheck?bno=" + ${modify.bno}
								+ "&inputpasswd="+inputpasswd.value;
			});
			
			function check() {
				var title = document.getElementById("title");
				var content = document.getElementById("content");
				var writer = document.getElementById("writer");
				var inputpasswd = document.getElementById("inputpasswd");
				var checkvalue;

				if (!title.value) {
					alert("제목를 입력해주세요");
					title.focus();
					return false;
				} else if (!content.value) {
					alert("내용를 입력해주세요");
					content.focus();
					return false;
				} else if (!inputpasswd.value) {
					alert("비밀번호를 입력해주세요");
					inputpasswd.focus();
					return false;
				} else if (!byteCheck(title, 30, "제목")
						|| !byteCheck(content, 3000, "내용")
						|| !byteCheck(writer, 30, "작성자")
						|| !byteCheck(passwd, 30, "비밀번호")) {
					alert("no");
					return false;
				}
				return true;
			}

			function byteCheck(obj, max, checkvalue) {

				//var obj = document.getElementById("text");

				var maxByte = max; //최대 입력 바이트 수

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
					alert(checkvalue + "은 한글 최대 " + max / 2 + "자 영문 " + max
							+ "자 까지 입력할 수 있습니다");
					return false;
				}
				return true;
			}

			// 취소 버튼 클릭
			$("#cancel_btn").click(function() {
				history.back();
				/*
				 self.location = "/board/read?bno=${modify.bno}"
				 + "&page=${scri.page}"
				 + "&perPageNum=${scri.perPageNum}"
				 + "&searchType=${scri.searchType}"
				 + "&keyword=${scri.keyword}";
				 */
			});

			$("#list").click(
					function() {
						self.location = "/board/listSearch?bno=${modify.bno}"
								+ "&page=${scri.page}"
								+ "&perPageNum=${scri.perPageNum}"
								+ "&searchType=${scri.searchType}"
								+ "&keyword=${scri.keyword}";

					});
		</script>

	</div>

</body>
</html>






