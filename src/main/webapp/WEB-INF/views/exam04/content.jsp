<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div>
	<c:if test="${connStatus=='성공'}">
		<div class="alert alert-success">
			DB 연결 성공 
		</div>
	</c:if>
	
	<c:if test="${connStatus=='실패'}">
		<div class="alert alert-danger">
			DB 연결 실패 
		</div>
	</c:if>
	

	<p>1.  <a href="list">게시물 목록</a></p>
	<p>2.  <a href="createForm">게시물 입력</a></p>
	<p>3.  <a href="createFormWithAttach">게시물 입력(첨부 포함)</a></p>

</div>
	  
<%@ include file="/WEB-INF/views/common/footer.jsp" %>