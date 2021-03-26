<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div>
	
	<div class="alert alert=primary">
		로그인 
	</div>
	
	<c:if test="${loginError != null}">
		<div class="alert alert-danger">
			<c:if test="${loginError == 'wrongUid'}">
				<span>아이디가 존재하지 않습니다. </span>
			</c:if>
			<c:if test="${loginError == 'wrongUpassword'}">
				<span>비밀번호가 틀렸습니다.</span>
			</c:if>
		</div>
	</c:if>
	
     <form method="post" action="login">
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