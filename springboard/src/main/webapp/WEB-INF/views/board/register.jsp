<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<form role="form" method="post">
	<div class="box-body">
		<div class="form-group">
			<label for="title">Title</label> <input type="text" id="title"
				name="title" class="form-control" placeholder="Enter Title" />
		</div>
		<div class="form-group">
			<label for="content">Content</label>
			<textarea name="content" id="content" class="form-control" rows="3"
				placeholder="Enter"></textarea>
		</div>
		<div class="form-group">
			<label for="writer">Writer</label> <input type="text" name="writer"
				id="writer" class="form-control" placeholder="Enter Writer" />
		</div>
	</div>
	<div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</div>
</form>
</html>