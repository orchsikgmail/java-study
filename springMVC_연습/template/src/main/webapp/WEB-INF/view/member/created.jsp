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
.helpbox{
    padding: 5px;
    font-weight: 800;
}
.redfont{
	  color: red;
}
.bluefont{
	  color: blue;
}
</style>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>


<div class="responsive" style="margin: 10px 10px">
<form name="createdForm" method="post" enctype="multipart/form-data">
	<div class="form-group">
		<label for="id">아이디</label>
		<input type="text" class="form-control" id="id" name="id" value="${dto.id }">
		<label class="helpbox"></label>
	</div>
	
	<script type="text/javascript">
	$(document).on("change", "#id", function checkId(){
		var url = "<%=cp%>/member/checkId";
		var input_id = $('#id').val();
		var data = { id : input_id };
		
		$.ajax({
			url: url,
			data: data,
			type: "get",
			dataType: "json"
		}) 
		.done(function(json){ 
			var isUsed = json.isUsed;
			if(isUsed=='true'){
				$(".helpbox").text("이미 사용중인 아이디입니다.");
				$(".helpbox").attr('class','helpbox redfont');
			} else{
				$(".helpbox").text("사용가능한 아이디입니다.");
				$(".helpbox").attr('class','helpbox bluefont');
			}
		})
		.fail(function(json){
			
		});
	});
	</script>
	
	
	
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
		<label for="name">이름</label>
		<input type="text" class="form-control" id="name" name="name" value="${dto.name }">
	</div>
	

	<div style="text-align: center;">
		<button class="btn btn-sm btn-dark" type="button" onclick="checkInput()">${mode=='created'?"등록": "수정"}</button>
		<button class="btn btn-sm btn-dark" type="reset">재입력</button>
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
	var str = form.name.value;
	if(!str){
		alert("이름을 입력하세요.");
		form.name.focus();
		return;
	}

	var mode = "${mode}";
	form.action = "<%=cp%>/member/"+mode;
	
	form.submit();
}
</script>

