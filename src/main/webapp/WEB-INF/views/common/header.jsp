<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
   <title>Spring</title>
   <%-- applicaiton: ServletContext 객체(웹 애플리케이션 실행 정보를 가지고 있는 객체) 참조 --%>
   <link rel="stylesheet"
      href="<%=application.getContextPath() %>/resources/bootstrap-4.6.0/css/bootstrap.min.css">
   <script
      src="${pageContext.request.contextPath}/resources/jquery/jquery-3.5.1.min.js"></script>
   <script
      src="${pageContext.request.contextPath}/resources/bootstrap-4.6.0/js/bootstrap.bundle.min.js"></script>
   <script
      src="${pageContext.request.contextPath}/resources/bootstrap-4.6.0/js/bootstrap.min.js"></script>
</head>
<body ng-controller="mainController">
   <div class="d-flex flex-column vh-100" style="height: 2000px;">

      <nav
         class="navbar navbar-expand-sm bg-dark navbar-dark text-white font-weight-bold justify-content-between">
         <a class="navbar-brand" href="./"> <img
            src="${pageContext.request.contextPath}/resources/images/logo-spring.png"
            width="30" height="30" class="d-inline-block align-top">
            Spring
         </a>
         <div>
            <div>
            	<c:if test="${loginUid == null}">		
               		<a class="btn btn-success btn-sm"
               			href="<%=application.getContextPath()%>/exam07/loginForm">로그인</a>            	
            	</c:if>
            	<c:if test="${loginUid != null}"> 	 
            		<span >User: ${loginUid}</span>
               		<a class="btn btn-success btn-sm"
               				href="<%=application.getContextPath()%>/exam07/logout">로그아웃</a>
            	</c:if>
             </div>
         </div>
      </nav>

      <div class="flex-grow-1 container-fluid" style="height: 2000px;">
         <div class="row h-100">
            <div class="col-md-4 p-3 bg-dark">
               <div class="h-100 d-flex flex-column">
                  <div class="flex-grow-1"
                     style="height: 0px; overflow-y: auto; overflow-x: hidden;">
                     <%@ include file="/WEB-INF/views/common/menu.jsp"%>
                  </div>
               </div>
            </div>

            <div class="col-md-8 p-3">
               <div class=" h-100 d-flex flex-column">
                  <div class="flex-grow-1 overflow-auto pr-3" style="height: 0px">