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

	<div class="container">

		<section>

		<form role="form">
				<input type="hidden" id="page" name="page" value="${scri.page}"
					readonly="readonly" /> <input type="hidden" id="perPageNum"
					name="perPageNum" value="${scri.perPageNum}" readonly="readonly" />
				<input type="hidden" id="searchType" name="searchType"
					value="${scri.searchType}" readonly="readonly" /> <input
					type="hidden" id="keyword" name="keyword" value="${scri.keyword}"
					readonly="readonly" /> <input type="hidden" id="bno" name="bno"
					value="${read.bno }" />


			</form>

			<div class="form-group">
				<label for="title" class="col-sm-2 control-label">글 제목</label>
				<div class="col-sm-10">
					<input type="text" id="title" name="title" class="form-control"
						value="${read.title}" readonly="readonly" />
				</div>
			</div>
		

			<div class="form-group">
				<label for="content" class="col-sm-2 control-label">글 내용</label>
				<div class="col-sm-10">
					<textarea id="content" name="content" class="form-control"
						readonly="readonly">${read.content}</textarea>
				</div>
			</div>


			<div class="form-group">
				<label for="writer" class="col-sm-2 control-label">작성자</label>
				<div class="col-sm-10">
					<input type="text" id="writer" name="writer" class="form-control"
						value="${read.writer}" readonly="readonly" />
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">작성 날짜</label>
				<div class="col-sm-10">
					<span><fmt:formatDate value="${read.regDate}"
							pattern="yyyy-MM-dd" /></span>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">첨부파일</label>
				<div class="col-sm-10">
					<a href="/board/fileDown/${read.bno}">${read.fileOriname}</a>
				</div>
				
			</div>

			<div class="form-group">
				<button type="button" id="list_btn" class="btn btn-primary">목록</button>
				<button type="button" id="modity_btn" class="btn btn-warning">수정</button>
				<button type="button" id="delete_btn" class="btn btn-danger">삭제</button>

				<script>
					// 폼을 변수에 저장
					var formObj = $("form[role='form']");
					$("#cancel_btn").click(function() {
						history.back();
					})
					// 목록 버튼 클릭
					$("#list_btn")
							.click(
									function() {

										self.location = "/board/listSearch?"
												+ "page=${scri.page}&perPageNum=${scri.perPageNum}"
												+ "&searchType=${scri.searchType}&keyword=${scri.keyword}";
									});

					// 수정 버튼 클릭
					$("#modity_btn").click(function() {

						formObj.attr("action", "/board/modify");
						formObj.attr("method", "get");
						formObj.submit();

					});

					// 삭제 버튼 클릭
					$("#delete_btn").click(function() {

						formObj.attr("action", "/board/delete");
						formObj.attr("method", "get");
						formObj.submit();

					});
				</script>
			</div>

			<!--
			<div id="reply">
				<ol class="replyList">
				<c:forEach items="${repList}" var="repList">
				<li>
					<p>
					<span class="glyphicon glyphicon-user"></span>
						${repList.writer}
						(<fmt:formatDate value="${repList.regDate}" pattern="yyyy-MM-dd" />)
					</p>
					
					<p class="bg-info">${repList.content}</p>
					
					<div class="form-group">													
						<button type="button" class="replyUpdate btn btn-warning btn-xs" data-rno="${repList.rno}">수정</button>
						<button type="button" class="replyDelete btn btn-danger btn-xs" data-rno="${repList.rno}">삭제</button>
						
						<script> 
							$(".replyUpdate").click(function(){
								self.location = "/board/replyUpdate?bno=${read.bno}" + "&page=${scri.page}"
									+ "&perPageNum=${scri.perPageNum}" + "&searchType=${scri.searchType}"
									+ "&keyword=${scri.keyword}" + "&rno=" + $(this).attr("data-rno");								
							});
							
							$(".replyDelete").click(function(){
								self.location = "/board/replyDelete?bno=${read.bno}" + "&page=${scri.page}"
									+ "&perPageNum=${scri.perPageNum}" + "&searchType=${scri.searchType}"
									+ "&keyword=${scri.keyword}" + "&rno=" + $(this).attr("data-rno");	
							});							
						</script>
					</div>
				</li>
				</c:forEach>			
				</ol>
		<
		<section class="replyForm">
		<form role="form" method="post" autocomplete="off" class="form-horizontal">
		
			<input type="hidden" id="bno" name="bno" value="${read.bno}" readonly="readonly" />
			<input type="hidden" id="page" name="page" value="${scri.page}" readonly="readonly" />
			<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}" readonly="readonly" />
			<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}" readonly="readonly" />
			<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}" readonly="readonly" />
		
			<div class="form-group">
				<label for="writer" class="col-sm-2 control-label">작성자</label>
				<div class="col-sm-10">
					<input type="text" id="writer" name="writer" class="form-control" />
				</div>
			</div>			
			
			<div class="form-group">
				<label for="content" class="col-sm-2 control-label">댓글 내용</label>
				<div class="col-sm-10">
					<textarea id="content" name="content" class="form-control" ></textarea>
				</div>
			</div>
			
			<div class="form-group">
				 <div class="col-sm-offset-2 col-sm-10"> 
					<button type="button" class="repSubmit btn btn-success">작성</button> 
				</div>				
				<script>
				var formObj = $(".replyForm form[role='form']");
										
				$(".repSubmit").click(function(){
					formObj.attr("action", "replyWrite");
					formObj.submit();
				});				
				</script>				
			</div>
		</form>		
		</section>
			
-->
	</div>

	</section>


	</div>

</body>
</html>











