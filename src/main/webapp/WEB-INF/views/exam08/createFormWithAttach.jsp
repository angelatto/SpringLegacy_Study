<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div>

	<div class="alert alert-success">
		 게시물 입력 (첨부 포함)
	</div>

	<form method="post" action="createWithAttach?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
		<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> 너는 멀티파트로 넘어갈 수 없다. --%>
        <div class="form-group">
          <label for="btitle">제목</label>
          <input type="text" class="form-control" id="btitle" name="btitle">
          <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
          <label for="bcontent">내용</label>
          <input type="text" class="form-control" id="bcontent" name="bcontent">
        </div>
         <div class="form-group">
		    <label for="battach">첨부</label>
		    <input type="file" class="form-control-file" id="battach" name="battach" multiple>

		 </div>
  
        <button type="submit" class="btn btn-primary">저장</button>
   </form>
</div>
</body>
</html>
<!-- 
 <input type="file"> 이게 들어가면 무조건 enctype을 지정해줘야 한다.!!! 반드시 기억!! 
 enctype="multipart/form-data"
 -->