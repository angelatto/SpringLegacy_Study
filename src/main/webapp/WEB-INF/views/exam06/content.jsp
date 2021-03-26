<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*, com.mycompany.webapp.dto.*"%>
                  
<%@ include file="/WEB-INF/views/common/header.jsp" %>

   <div>
      <div class="alert alert-primary">
         상태 유지
      </div>
      
      <div>
      	<h6>쿠키를 이용한 상태 유지</h6>
	      	<a class="btn btn-info btn-sm" href="createCookie">쿠키 생성 (상태 저장 / 수정))</a>
	      	<a class="btn btn-info btn-sm" href="readCookie">쿠키 읽기 (상태 읽기)</a>
	      	<a class="btn btn-info btn-sm" href="deleteCookie">쿠키 삭제 (상태 삭제)</a>
      </div>
      
       <div class="mt-3">
      	<h6>세션 이용</h6>
	      	<a class="btn btn-info btn-sm" href="sessionSaveObject">세션에 객체 생성 (상태 저장 / 수정))</a>
	      	<a class="btn btn-info btn-sm" href="sessionReadObject">세션에서 객체 찾기 및  읽기 (상태 읽기)</a>
	      	<a class="btn btn-info btn-sm" href="sessionRemoveObject"> 세션에 객체 삭제 (상태 삭제)</a>
      </div>
    
   </div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>