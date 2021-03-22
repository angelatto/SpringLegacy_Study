<%-- page 지시자 --%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mycompany.webapp.dto.*"%>

<%-- taglib 지시자 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<div>
	<table class="table">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>글쓴이</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>

		<c:forEach var="board" items="${list}">
			<tr>
				<td>${board.bno}</td>
				<td>${board.btitle}</td>
				<td>${board.bcontent}</td>
				<td>${board.bwriter}</td>
				<td><fmt:formatDate value="${board.bdate}" pattern="yyyy-MM-dd"/></td>
				<td>${board.bhitcount}</td>
			</tr>
		</c:forEach>
	</table>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>