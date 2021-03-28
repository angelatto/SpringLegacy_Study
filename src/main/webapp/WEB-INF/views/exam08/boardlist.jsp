<%-- page 지시자 --%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mycompany.webapp.dto.*"%>

<%-- taglib 지시자 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<%@ include file="/WEB-INF/views/common/header.jsp"%>
 <%-- 메뉴 내용 부분 --%>
  <div class="card">
     <div class="card-header">
        ${role} 게시물 목록
     </div>
     <div class="card-body">
        <table class="table">
          <tr>
             <th>번호</th>
             <th>제목</th>
             <th>글쓴이</th>
             <th>날짜</th>
             <th>조회수</th>
          </tr>

		<c:forEach var="board" items="${list}">
			<tr>
				<td>${board.bno}</td>
				<td><a href="read?bno=${board.bno}">${board.btitle}</a></td>
				<td>${board.bcontent}</td>
				<td>${board.bwriter}</td>
				<td><fmt:formatDate value="${board.bdate}" pattern="yyyy-MM-dd"/></td>
				<td>${board.bhitcount}</td>
			</tr>
		</c:forEach>
		<tr> <!-- [처음][이전] 1 2 3 4 5 [다음][맨끝] -->
			<td colspan="5" class="text-center">
				<div class="d-flex">
					<div class="flex-grow-1">
						<a class="btn btn-outline-primary btn-sm"
							href="list?pageNo=1"> 처음</a>
						<c:if test="${pager.groupNo > 1}">
								<a class="btn btn-outline-primary btn-sm"
									href="list?pageNo=${pager.startPageNo-1}">이전</a>
						</c:if>
						<c:forEach var="i" begin="${pager.startPageNo}" end="${pager.endPageNo}">
							<a class="btn 
								<c:if test='${pager.pageNo == i}'>btn-outline-danger</c:if>
								<c:if test='${pager.pageNo != i}'>btn-outline-success</c:if>
								 btn-sm" href="list?pageNo=${i}">${i}</a>
						</c:forEach>
						<c:if test="${pager.groupNo < pager.totalGroupNo}">
								<a class="btn btn-outline-primary btn-sm"
									href="list?pageNo=${pager.endPageNo+1}"> 다음</a>
						</c:if>
						<a class="btn btn-outline-primary btn-sm"
							href="list?pageNo=${pager.totalPageNo}"> 맨끝</a>
					</div>
					  <sec:authorize access="isAuthenticated()" >
						 <a class="btn btn-success btn-sm" href="createForm">글쓰기</a>
	                </sec:authorize>
				</div>
			</td>
		</tr>
	</table>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>