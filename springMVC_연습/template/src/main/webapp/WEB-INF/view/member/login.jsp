<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String cp = request.getContextPath(); %>


<link rel="stylesheet" type="text/css" href="<%=cp%>/board/css/board-basic.css">
<style type="text/css">
.input-group{
	margin-bottom: 10px;
}
</style>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>


<div class="responsive" style="margin: 10px 10px">
<form name="createdForm" method="post" enctype="application/x-www-form-urlencoded">
	<div class="form-group">
		<label for="id">아이디</label>
		<input type="text" class="form-control" id="id" name="id" value="${dto.id }">
	</div>
	<div class="form-group">
		<label for="password">비밀번호</label>
		<input type="password" class="form-control" id="password" name="pwd">
	</div>

	<div style="text-align: center;">
		<button class="btn btn-sm btn-dark" type="button" onclick="checkInput()">로그인</button>
		<button class="btn btn-sm btn-dark" type="button" onclick="javascript:location.href='<%=cp%>/';">취소</button>
	</div>
</form>
</div>


<script type="text/javascript">
function checkInput(){
	var form = document.createdForm;
	
	var str = form.id.value;
	if(!str){
		alert("아이디를 입력하세요.");
		form.id.focus();
		return;
	}
	var str = form.password.value;
	if(!str){
		alert("비밀번호를 입력하세요.");
		form.password.focus();
		return;
	}

	form.action = "<%=cp%>/member/loginSubmit";
	
	form.submit();
}
</script>