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
.row{
	margin-bottom: 10px;
}

.nowrap {
	white-space: nowrap;
	max-width: 150px;
}
.ellipsis{
	white-space: nowrap;
	overflow: hidden;
	white-space: ellipsis;
}
</style>

<div class="responsive" style="margin: 10px 10px">
	<form name="updateForm" action="<%=cp%>/mfboard/updateForm" method="post">
	<div class="form-row">
		<div class="form-group col-md-4">
			<label for="name">이름</label>
			<input type="text" class="form-control" id="name" name="name" value="${dto.name }"  readonly="readonly">
		</div>
		<div class="form-group col-md-4">
			<label for="created">작성일</label>
			<input type="text" class="form-control" id="created" value="${dto.created }"  readonly="readonly">
		</div>
		<div class="form-group col-md-4">
			<label for="notice">공지여부</label>
			<input type="checkbox" class="form-control" style="width:50px;" id="notice" name="notice" onclick="return false;" value="1" ${dto.notice==1? "checked='checked'":"" }>
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
	
	<div class="form-group">
		<label for="subject">첨부파일</label>
		<c:forEach var="dto" items="${listFile }">
		<div class="input-group mb-3">
		  <input readonly="readonly" type="text" class="form-control" value="${dto.originalFilename }">
		  <div class="input-group-append">
			  <input readonly="readonly" type="text" class="form-control" 
			  	value="<fmt:formatNumber value='${dto.fileSize/1024}' pattern='0.00' />KByte">
		  </div>
		  <div class="input-group-append">
		    <button class="btn btn-outline-secondary" type="button" 
		    	onclick="javascript:location.href='<%=cp%>/mfboard/download?fileNum=${dto.fileNum }';">Down</button>
		  </div>
		</div>
		</c:forEach>
	</div>
	
	
	<div class="row align-items-center">
		<div class="col">ipAddr: 238.124.857.526</div>
		<div class="col" style="text-align: right;">hitCount: ${dto.hitCount }</div>
	</div>

	<div class="row" style="margin-bottom: 5px">
		<div class="col-2 nowrap">이전글</div>
		<div class="col ellipsis">
			<c:if test="${not empty preReadDto }">
			<a href="<%=cp %>/mfboard/article?${query}&num=${preReadDto.num}" style="color: black;">${preReadDto.subject }</a>
			</c:if>
		</div>
	</div>
	<div class="row">
		<div class="col-2 nowrap">다음글</div>
		<div class="col ellipsis">
			<c:if test="${not empty nextReadDto }">
			<a href="<%=cp %>/mfboard/article?${query}&num=${nextReadDto.num}" style="color: black;">${nextReadDto.subject }</a>
			</c:if>
		</div>
	</div>
	
	<div style="text-align: center;">
		<input type="hidden" name="num" value="${dto.num }">
		<button class="btn btn-sm btn-dark" type="button" onclick="javascript:location.href='<%=cp%>/mfboard/update?num=${dto.num}&page=${page}';">수정</button>
		<button class="btn btn-sm btn-dark" type="button" onclick="javascript:location.href='<%=cp%>/mfboard/reply?num=${dto.num}&page=${page}';">답변</button>
		<button class="btn btn-sm btn-dark" type="button" onclick="deleteBoard(${dto.num});">삭제</button>
		<button class="btn btn-sm btn-dark" type="button" onclick="javascript:location.href='<%=cp%>/mfboard/list?${query }';">리스트</button>
	</div>
	</form>
	
</div>




<script type="text/javascript">
function deleteBoard(num) {
	if(!confirm("게시글을 삭제하시겠습니까?")){
		return;
	}
	
	var url = "<%=cp%>/mfboard/delete?num="+num+"&${query }";
	location.href = url;
}
</script>
