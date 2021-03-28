<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>
	.error{
		font-size: 0.8em;
	}
</style>

<script>
	function validate(){
		event.preventDefault();  // 서버에 폼을 자동적으로 넘기는기능을 끈다. -> submit() 메소드를 호출하면 서버에 폼 요청이 간다. 
		
		var result = true
		// 유효성 검사 코드 
		const uid = $("#uid").val();
		const uname = $("#uname").val();
		const upassword = $("#upassword").val();
		const uemail = $("#uemail").val();
		  
		   
	    if(uid === "") {
	    	result = false;
	        $("#errorUid").html("필수 사항 입니다.")
	    }else if(uid.length < 8) {
	    	result = false;
	    	$("#errorUid").html("최소 8자 이상 입력해야 합니다.  ");
	    }else if(uid.length > 15){
	    	result = false;
	    	$("#errorUid").html("최대 15자 까지만 입력해야 합니다. ");
	    }
	      
	    if(uname === "") {
	        result = false;
	        $("#errorUname").html("필수 사항 입니다.")
	    }
	      
	    if(upassword === "") {
	    	result = false;
	        $("#errorUpassword").html("필수 사항 입니다.")
	    }  
		
	    if(uemail === ""){
	         result = false;
	         $("#errorUemail").html("필수 사항입니다.");
	    } else {
	         var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	         if (!re.test(uemail)) {
	            result = false;
	            $("#errorUemail").html("이메일 형식이 아닙니다.");
	         }
	    }
	 
		console.log("test 진행 ");
		console.log($("#uid").val()); // 사용자가 입력한 value 값 
		console.log($("#uid")[0]); // input  태그 자체 
		console.log($("#joinForm")[0]); // 폼태그 자체 
		
		if(result){ // 유효성 통과 
			$("#joinForm")[0].submit(); // [0]은 element 객체 찾기 
			// document.joinForm.submit();  폼 네임으로 보낼 수 도 있따. 
		}
		
	}

</script>
<div>
	
	<div class="alert alert=primary">
		회원 가입 
	</div>
	
     <form id="joinForm" method="post" action="join" onsubmit="validate()" novalidate>
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="form-group">
          <label for="uid">아이디</label>
          <input type="text" class="form-control" id="uid" name="uid">
          <span id="errorUid" class="text-danger error">에러 대응</span>
        </div>
        <div class="form-group">
          <label for="uname">이름</label>
          <input type="text" class="form-control" id="uname" name="uname">
          <span id="errorUname" class="text-danger error">에러 대응</span>
        </div>
        <div class="form-group">
          <label for="upassword">비밀번호</label>
          <input type="password" class="form-control" id="upassword" name="upassword">
          <span id="errorUpassword" class="text-danger error">에러 대응</span>
        </div>
         <div class="form-group">
          <label for="uemail">이메일 </label>
          <input type="email" class="form-control" id="uemail" name="uemail">
          <span id="errorUemail" class="text-danger error">에러 대응</span>
        </div>
        <button type="submit" class="btn btn-primary">가입</button>
   </form>
 </div>
	  
 
<%@ include file="/WEB-INF/views/common/footer.jsp" %>