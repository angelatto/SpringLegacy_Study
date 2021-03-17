<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 방법1 --%>
<%-- <%response.sendRedirect(application.getContextPath() + "/home"); %> --%>


<%-- 방법2 context root 생략 --%>
<c:redirect url="/home" />