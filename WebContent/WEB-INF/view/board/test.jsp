<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form role="form" method="GET" action="/board/test"
		enctype="multipart/form-data">

		<input type="text" id="text" name="text" />
		
	</form>
	<script type=text/javascript>
			function check() {
				var title = document.getElementById("text");
			
				if (!title.value) {
					alert("제목를 입력해주세요");
					title.focus();
					return false;
				} else{
					byteCheck(title,10,"제목");
				}
			}
			
			function byteCheck(obj, max, str) {
			    var maxByte = max; //최대 입력 바이트 수
			    var str = obj.value;
			    var str_len = str.length;
			 
			    var rbyte = 0;
			    var rlen = 0;
			    var one_char = "";
			    var str2 = "";
			 	out.println("bytecheck");
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
			        alert(str + "의 내용은 한글 " + (maxByte / 2) + "자 / 영문 " + maxByte + "자를 초과 입력할 수 없습니다.");
			        str2 = str.substr(0, rlen); //문자열 자르기
			        obj.value = str2;
			        history.back();
			    } 
			}

	</script>

</body>
</html>