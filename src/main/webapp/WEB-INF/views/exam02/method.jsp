<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
  
<div>
 <%-- 메뉴 내용 부분 --%>
method.jsp
 <hr/>
 	<div class="alert alert-primary">
 		현재 요청 방식: <span id="method">${method}</span>
	 </div>
	 
	 <h3>&lt; form &gt; 이용 </h3>
     <form method="get" action="method3" class="mb-2 d-inline-block">
	     <input class="btn btn-success btn-sm" type="submit" value="GET 전송">
    </form>
     <form method="post" action="method4" class="mb-2 d-inline-block">
	     <input class="btn btn-success btn-sm" type="submit" value="POST 전송">
    </form>
  <!--    <form method="put" action="method5" class="mb-2 d-inline-block">
	     <input class="btn btn-success btn-sm" type="submit" value="PUT 전송">
    </form>
     <form method="delete" action="method6" class="mb-2 d-inline-block">
	     <input class="btn btn-success btn-sm" type="submit" value="DELETE 전송">
    </form> -->
    
    <hr/>
    
    <h3> AJAX 이용 </h3>
    <button class="btn btn-info" onclick="sendGet()"> AJAX GET 방식 요청 </button>
    <script>
    	const sendGet = () => {
    		$.ajax({url: "ajaxMethod3", method: "get"})
    			.then(data => {		
    			/* 	
    				console.log("response"); 
    				응답 내용이 완전한 html이여서 응답을 이용하지 않았다. 
    				AJAX 통신을 하는 이유는 브라우저를 전체를 로드하는 것이 아니라,
    				부분적으로 html을 갱신하기 위함이다. 
    				
    				AJAX 응답으로 올 수 있는 것은 2가지이다. - html 코드 조각 / JSON 문자열데이터 
    			*/
    			$("#method").html(data.method); // {method : "GET"}
    			
    		});
    	};
    </script>
    <button class="btn btn-info" onclick="sendPost()"> AJAX POST 방식 요청 </button>
     <script>
    	const sendPost = () => {
    		$.ajax({url: "ajaxMethod3", method: "post"})
    			.then(data => {
    				$("#method").html(data.method);
    		});
    	};
    </script>
    <button class="btn btn-info" onclick="sendPut()"> AJAX PUT 방식 요청 </button>
     <script>
    	const sendPut = () => {
    		$.ajax({ url: "ajaxMethod3", method: "put"})
    			.then(data => {
    				$("#method").html(data.method);
    		});
    	};
    </script>
    <button class="btn btn-info" onclick="sendDelete()"> AJAX DELETE 방식 요청 </button>
	  <script>
	    	const sendDelete = () => {
	    		$.ajax({ url: "ajaxMethod3", method: "delete"})
	    			.then(data => {
	    				$("#method").html(data.method);
	    		});
	    	};
	    </script>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>