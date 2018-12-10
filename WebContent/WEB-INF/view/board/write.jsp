<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
<head>
<title>kuzuro 게시판</title>
</head>
<!-- 제이쿼리 -->
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>

<body>

	<div id="root">


		<section id="container">


			<form role="form" method="post" enctype="multipart/form-data"
				action="/board/write">
				<p>
					<label>글 제목</label><input type="text" id="title" name="title" />
				</p>
				<p>
					<label>글 내용</label>
					<textarea id="content" name="content"></textarea>
				</p>

				<p>
					<label>작성자</label> <input type="text" id="writer" name="writer" />
				</p>

				<p>
					<label>비밀번호</label> <input type="text" id="passwd" name="passwd" />
				</p>
				<label>첨부파일</label> <input type="file" id="files" name="files">
				<p>
					<button type="submit" onclick="return check();">작성</button>
				</p>
				<p>
					<button type="button" onClick="history.back();">취소</button>
				</p>

			</form>

		</section>
		<script type=text/javascript>
			function check() {
				var title = document.getElementById("title");
				var content = document.getElementById("content");
				var writer = document.getElementById("writer");
				var passwd = document.getElementById("passwd");

				if (!title.value) {
					alert("제목를 입력해주세요");
					title.focus();
					return false;
				} else if (!content.value) {
					alert("내용를 입력해주세요");
					content.focus();
					return false;
				} else if (!writer.value) {
					alert("작성자를 입력해주세요");
					writer.focus();
					return false;
				} else if (!passwd.value) {
					alert("비밀번호를 입력해주세요");
					passwd.focus();
					return false;
				} else if (!byteCheck(title, 30, "제목")
						|| !byteCheck(content, 3000, "내용")
						|| !byteCheck(writer, 30, "작성자")
						|| !byteCheck(passwd, 30, "비밀번호")) {
					alert("oo");
					return false;
				} else
					return true;
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
		</script>


	</div>

</body>
</html>
