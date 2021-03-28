<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div>
	
	<div class="alert alert=primary">
		로그인 
	</div>
	
	<!-- 시큐어리티가 제공하는 로그인 경로를 반드시 써야한다. /login -->
     <form method="post" action="<%=application.getContextPath()%>/login">
     	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="form-group">
          <label for="uid">아이디</label>
          <input type="text" class="form-control" id="uid" name="uid">
        </div>
        <div class="form-group">
          <label for="uname">이름</label>
          <input type="text" class="form-control" id="uname" name="uname">
        </div>
        <div class="form-group">
          <label for="upassword">비밀번호</label>
          <input type="password" class="form-control" id="upassword" name="upassword">
        </div>
        <button type="submit" class="btn btn-primary">로그인</button>
   </form>
 </div>
	  
 
<%@ include file="/WEB-INF/views/common/footer.jsp" %>