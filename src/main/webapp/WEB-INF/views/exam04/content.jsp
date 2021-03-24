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
	

	<p><a href="list">게시물 목록</a></p>
	<p><a href="createForm">게시물 입력</a></p>

</div>
	  
<%@ include file="/WEB-INF/views/common/footer.jsp" %>