<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div>

	<div class="alert alert-success">
		게시물 내용 
	</div>

	 <div class="form-group">
          <label>제목</label>
          <input type="text" class="form-control" value="${board.btitle}" readonly>
     </div>
     <div class="form-group">
          <label>내용</label>
          <input type="text" class="form-control" value="${board.bcontent}" readonly>
     </div>
     <div class="form-group">
          <label>글쓴이</label>
          <input type="text" class="form-control" value="${board.bwriter}" readonly>
     </div>
      <div class="form-group">
          <label>날짜</label>
          <input type="text" class="form-control"
				value='<fmt:formatDate value="${board.bdate}" pattern="yyyy-MM-dd"/>' readonly>
     </div>
      <div class="form-group">
          <label>조회수</label>
          <input type="text" class="form-control" value="${board.bhitcount}" readonly>
     </div>
   
   	  <div>
   	  	<a href="list">목록</a>
   	  	<a href="updateForm?bno=${board.bno}">수정</a>
   	  	<a href="delete?bno=${board.bno}">삭제</a>
      </div>
</div>
	  
<%@ include file="/WEB-INF/views/common/footer.jsp" %>