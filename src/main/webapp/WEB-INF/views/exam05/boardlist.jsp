<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="card">
   <div class="card-header">
      게시물 목록
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
                              <td><a href="javascript:read(${board.bno})">${board.btitle}</a></td>
                              <td>${board.bwriter}</td>
                              <td><fmt:formatDate value="${board.bdate}" pattern="yyyy-MM-dd"/></td>
                              <td>${board.bhitcount}</td>
                           </tr>
                        </c:forEach>
                  <tr>
                     <td colspan="5" class="text-center">
                     <div class="d-flex">
                        <div class="flex-grow-1">
                           <!-- [처음][이전] 1 2 3 4 5 [다음][맨끝] -->
                              <button class="btn btn-outline-primary btn-sm"
                                 onclick="getList(1)">처음</button>
                              <c:if test="${pager.groupNo>1}">
                                 <button class="btn btn-outline-info btn-sm"
                                    onclick="getList(${pager.startPageNo-1})">이전</button>
                              </c:if>   
                              <c:forEach var="i" begin="${pager.startPageNo}" end="${pager.endPageNo}">
                                 <c:if test="${pager.pageNo!=i}">
                                 <button class="btn btn-outline-success btn-sm" onclick="getList(${i})">${i}</button>
                                 </c:if>
                                 
                                 <c:if test="${pager.pageNo==i}">
                                 <button class="btn btn-danger btn-sm" onclick="getList(${i})">${i}</button>
                                 </c:if>
                              </c:forEach>
                              <c:if test="${pager.groupNo<pager.totalGroupNo}">
                                 <button class="btn btn-outline-info btn-sm"
                                    onclick="getList(${pager.endPageNo+1})">다음</button>
                              </c:if>
                              <button class="btn btn-outline-primary btn-sm"
                                 onclick="getList(${pager.totalPageNo})">맨끝</button>
                           </div>
                           
                            <c:if test="${loginUid != null}">		
	                           <button class="btn btn-success btn-sm" 
	                                 onclick="createForm()">글쓰기</button>
	                        </c:if> 
                        </div>
                     </td>
                  </tr>
      </table>
   </div>
</div>