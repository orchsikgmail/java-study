<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String cp = request.getContextPath(); %>

<link rel="stylesheet" type="text/css" href="<%=cp%>/board/css/board-basic.css">


<div class="responsive" style="margin: 10px 10px">
<form name="createdForm" method="post">
	<div class="form-group">
		<label for="name">이름</label>
		<input type="text" class="form-control" id="name" name="name" value="${dto.name }">
	</div>
	<div class="form-row">
		<div class="form-group col-md-6">
			<label for="password">비밀번호</label>
			<input type="password" class="form-control" id="password" name="pwd">
		</div>
		<div class="form-group col-md-6">
			<label for="password2">비밀번호 확인</label>
			<input type="password" class="form-control" id="password2">
		</div>
	</div>
	<div class="form-group">
		<label for="subject">제목</label>
		<input type="text" class="form-control" id="subject" name="subject" value="${dto.subject }">
	</div>
	<div class="form-group">
	  <label for="content">내용</label>
	  <textarea class="form-control" id="content" name="content" rows="5">${dto.content }</textarea>
	</div>

	<div style="text-align: center;">
		<c:if test="mode=='update'">
			<input type="hidden" name="num" value="${dto.num }">
		</c:if>
		<button class="btn btn-sm btn-dark" type="button" onclick="checkInput()">${mode=='created'?"등록":"수정완료"}</button>
		<button class="btn btn-sm btn-dark" type="reset">재입력</button>
		<button class="btn btn-sm btn-dark" type="button" onclick="javascript:location.href='<%=cp%>/nBoard/list';">취소</button>
	</div>
</form>
</div>


<script type="text/javascript">
function checkInput(){
	var form = document.createdForm;
	
	var str = form.name.value;
	if(!str){
		alert("이름을 입력하세요.");
		form.name.focus();
		return;
	}
	var str = form.password.value;
	if(!str){
		alert("비밀번호를 입력하세요.");
		form.password.focus();
		return;
	}
	var str = form.password2.value;
	if(!str){
		alert("비밀번호를 입력하세요.");
		form.password2.focus();
		return;
	}
	if(form.password.value != form.password2.value){
		alert("비밀번호가 서로 다릅니다.");
		form.password2.focus();
		return;
	}
	var str = form.subject.value;
	if(!str){
		alert("제목을 입력하세요.");
		form.subject.focus();
		return;
	}
	var str = form.content.value;
	if(!str){
		alert("내용을 입력하세요.");
		form.content.focus();
		return;
	}

	var mode = "${mode}";
	if(mode=='created'){
		form.action = "<%=cp%>/nBoard/created";	
	} else {
		form.action = "<%=cp%>/nBoard/update";	
	}
	
	form.submit();
}
</script>