<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div>
	uid: ${user.uid}  <br/>
    uemail: ${user.uname} <br/>
    upassword: ${user.upassword} <br/>
    uhobby: 
    	<c:forEach var="hobby" items="${user.uhobby}">
    		${hobby},
    	</c:forEach>  <br/>
    ujob: ${user.ujob}  <br/>
 </div>

<%@ include  file="/WEB-INF/views/common/footer.jsp"%>