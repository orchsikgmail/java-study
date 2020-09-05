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
<form name="createdForm" method="post" enctype="multipart/form-data">
	<div class="form-row">
		<div class="form-group col-md-6">
			<label for="name">이름</label>
			<input type="text" class="form-control" id="name" name="name" value="${dto.name }">
		</div>
		<div class="form-group col-md-6">
			<label for="notice">공지여부</label>
			<input type="checkbox" class="form-control" style="width:50px;" id="notice" name="notice" value="1" ${dto.notice==1? "checked='checked'":"" }>
		</div>
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

	<div id="input-div">
		<div class="input-group" >
		  <div class="custom-file">
		    <input type="file" class="custom-file-input" id="upload1" name="upload">
		    <label class="custom-file-label" for="upload1" id="upload1-label">Choose file</label>
		  </div>
		</div>
	</div>
	
	<c:if test="${mode =='update' }">
	<div class="form-group">
		<label for="subject">첨부파일</label>
		<c:forEach var="dto" items="${listFile }">
		<div class="input-group mb-3" id="f${dto.fileNum }">
		  <input readonly="readonly" type="text" class="form-control" value="${dto.originalFilename }">
		  <div class="input-group-append">
		    <button class="btn btn-outline-secondary" type="button" onclick="deleteFile(${dto.fileNum})">Delete</button>
		  </div>
		</div>
		</c:forEach>
	</div>
	</c:if>

	<div style="text-align: center;">
		<button class="btn btn-sm btn-dark" type="button" onclick="checkInput()">${mode=='created'?"등록": mode=='update'?"수정완료":"답변완료"}</button>
		<button class="btn btn-sm btn-dark" type="reset">재입력</button>
		<button class="btn btn-sm btn-dark" type="button" onclick="javascript:location.href='<%=cp%>/mfboard/list';">취소</button>
		
		<c:if test="${mode == 'update' }">
			<input type="hidden" name="page" value="${page}">
			<input type="hidden" name="num" value="${dto.num }">
		</c:if>
		<c:if test="${mode == 'reply' }">
			<input type="hidden" name="page" value="${page }">
			<input type="hidden" name="parent" value="${dto.num }">
			<input type="hidden" name="groupNum" value="${dto.groupNum }">
			<input type="hidden" name="orderNum" value="${dto.orderNum }">
			<input type="hidden" name="depth" value="${dto.depth }">
		</c:if>
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
	form.action = "<%=cp%>/mfboard/"+mode;
	
	form.submit();
}
</script>

<script type="text/javascript">
$(document).on("change", ".custom-file-input", function processInputForm(){
	// 유효값 확인
	var $id = $(this).attr("id");
	if(! $("#"+$id).val()){
		return;
	}
	// 입력받은 파일이름 출력
	var fakeFilename = $("#"+$id).val();
	var sidx = fakeFilename.lastIndexOf('\\');
	var filename = fakeFilename.substring(sidx+1);
	$("#"+$id+"-label").text(filename);
	
	// inputForm 추가
	var idx = (Number)($id.substring(6))+1;
	var $outdiv, $indiv, $input, $label;
	$input = $("<input>", {type:"file", class:"custom-file-input", id:"upload"+idx, name:"upload"});
	$label = $("<label>", { class:"custom-file-label", for:"upload"+idx, id:"upload"+idx+"-label" } );
	$indiv = $("<div>", { class:"custom-file" });
	$outdiv = $("<div>", { class:"input-group" });
	
	$indiv.append($input);
	$indiv.append($label);
	$outdiv.append($indiv);
	
	$("#input-div").append($outdiv);
});
</script>

<script type="text/javascript">
function deleteFile(fileNum){
	var url = "<%=cp%>/mfboard/deleteFile";
	
	$.post(url, {fileNum:fileNum}, function(data){
		$("#f"+fileNum).remove();
	}, "JSON");
	
}
</script>







