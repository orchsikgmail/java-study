<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String cp = request.getContextPath(); %>

<link rel="stylesheet" type="text/css" href="<%=cp%>/board/css/board-basic.css">
<style>
.form-control[readonly]{
    background-color: white;
    opacity: 1;
}
</style>

<div class="responsive" style="margin: 10px 10px">
	<form name="updateForm" action="<%=cp%>/nBoard/updateForm" method="post">
	<div class="form-row">
		<div class="form-group col-md-6">
			<label for="name">이름</label>
			<input type="text" class="form-control" id="name" name="name" value="${dto.name }"  readonly="readonly">
		</div>
		<div class="form-group col-md-6">
			<label for="created">작성일</label>
			<input type="text" class="form-control" id="created" value="${dto.created }"  readonly="readonly">
		</div>
	</div>
	<div class="form-group">
		<label for="subject">제목</label>
		<input type="text" class="form-control" id="subject" name="subject" value="${dto.subject }"  readonly="readonly">
	</div>
	<div class="form-group">
	  <label for="content">내용</label>
	  <textarea class="form-control" id="content" name="content" rows="5" readonly="readonly" style="resize: none;">${dto.content}</textarea>
	</div>
	<div class="row align-items-center">
		<div class="col">ipAddr: ${dto.ipAddr }</div>
		<div class="col" style="text-align: right;">hitCount: ${dto.hitCount }</div>
	</div>


	<div style="text-align: center;">
		<input type="hidden" name="num" value="${dto.num }">
		<button class="btn btn-sm btn-dark" type="submit">수정</button>
		<button class="btn btn-sm btn-dark" type="button" onclick="deleteBoard(${dto.num});">삭제</button>
		<button class="btn btn-sm btn-dark" type="button" onclick="javascript:location.href='<%=cp%>/nBoard/list?${query }';">리스트</button>
	</div>
	</form>
</div>

<script type="text/javascript">
function deleteBoard(num) {
	if(!confirm("게시글을 삭제하시겠습니까?")){
		return;
	}
	
	var url = "<%=cp%>/nBoard/delete?num="+num;
	location.href = url;
}
</script>
