<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" Content="text/html; charset=utf-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<html>
<head>
<title>kuzuro 게시판</title>


<!-- 제이쿼리 -->
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>

<style type="text/css">
ul li{
	list-style: none;
	margin: 0;
	padding: 0 5px;
	 display:inline; 
	 margin:auto;
}

table {
	width: 100%;
	line-height: 21px;
	border-top: 1px solid #cccccc;
	border-left: 1px solid #cccccc;
	border-collapse: collapse;
}

table th, table td {
	color: #678197;
	text-align: center;
	border-right: 1px solid #cccccc;
	border-bottom: 1px solid #cccccc;
	padding: 3px 0;
	text-align: center;
}

table th {
	background-color: #eeeeee;
}


</style>

</head>
<body>
	<!-- <div id="root"> -->
	<div class="container">


		<!-- <section id="container"> -->
		<section>
			<h2>글 목록</h2>

			<table class="table table-hover">
				<thead>
					<tr>
						<th>글 번호</th>
						<th>글 제목</th>
						<th>작성자</th>
						<th>작성일자</th>
						<th>조회수</th>
					</tr>
				</thead>
				<!-- 목록 시작 -->
				<c:forEach items="${list}" var="list">
					<tr>
						<td>${list.bno}</td>
						<%-- <td><a href="/board/read?bno=${list.bno}">${list.title}</a></td> --%>

						<td><a
							href="/board/read?bno=${list.bno}&
										page=${scri.page}&
										perPageNum=${scri.perPageNum}&
										searchType=${scri.searchType}&
										keyword=${scri.keyword}">${list.title}</a>
						</td>

						<td>${list.writer}</td>
						<td><fmt:formatDate value="${list.regDate}"
								pattern="yyyy-MM-dd" /></td>
						<td>${list.viewCnt }</td>
					</tr>
				</c:forEach>
				<!-- 목록 끝 -->
			</table>
			
			<div class="wirte row" style="float:right;">
				<a href="/board/write">글 작성</a>
			</div>
			
			<div class="search row">
				<div class="col-xs-2 col-sm-2">
					<select name="searchType" class="form-control">
						<option value="n"
							<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
						<option value="t"
							<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
						<option value="c"
							<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
						<option value="w"
							<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
						<option value="tc"
							<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
					</select>
				</div>

				<div class="col-xs-10 col-sm-10">
					<div class="input-group">
						<input type="text" name="keyword" id="keywordInput"
							value="${scri.keyword}" class="form-control" /> <span
							class="input-group-btn">
							<button id="searchBtn" class="btn btn-default">검색</button>
						</span>
					</div>
				</div>

				<script>
					$(function() {
						$('#searchBtn').click(
								function() {
									self.location = "listSearch"
											+ '${pageMaker.makeQuery(1)}'
											+ "&searchType="
											+ $("select option:selected").val()
											+ "&keyword="
											+ encodeURIComponent($(
													'#keywordInput').val());
								});
					});
				</script>
			</div>


			<div class="col-md-offset-3" style="text-align:center">
				<ul class="pagination">
					<c:if test="${pageMaker.prev}">
						<li><a
							href="listSearch${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
					</c:if>

					<c:forEach begin="${pageMaker.startPage}"
						end="${pageMaker.endPage}" var="idx">
						<li
							<c:out value="${pageMaker.cri.page == idx ? 'class=active' : ''}"/>>
							<a href="listSearch${pageMaker.makeSearch(idx)}">${idx}</a>
						</li>
					</c:forEach>

					<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						<li><a
							href="listSearch${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
					</c:if>
				</ul>
			</div>


		</section>

	</div>
</body>
</html>







