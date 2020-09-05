<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String cp = request.getContextPath(); %>

<link rel="stylesheet" type="text/css" href="<%=cp%>/board/css/board-basic.css">


<div class="table-responsive" > 
<span class="helpBox">&nbsp;${msg }</span> 
<table class="table table-striped table-bordered table-hover"> 
	<caption>Page ${page }/${total_page } : ${dataCount }개</caption> 
	<thead class="thead-dark">
		<tr>
			<th scope="col">#</th>
			<th scope="col">subject</th> 
			<th class="td_center" scope="col">name</th>
			<th class="td_center" scope="col">created</th>
			<th class="td_center" scope="col">hit</th>
		</tr>
	</thead>
		
	<tbody>
		<c:forEach var="dto" items="${boardList}">
			<tr class="tr_data" data-num="${dto.num }">
				<th scope="row">${dto.num }</th>
				<td class="td_subject">${dto.subject }</td>
				<td class="td_center">${dto.name }</td>
				<td class="td_center">${dto.created }</td>
				<td class="td_center">${dto.hitCount }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>


<div class="input-group input-group2 mb-3">
	<div class="input-group-prepend">
		<select class="custom-select" name="condition" id="condition">
			<option value="all" selected="selected">All</option>
			<option value="subject">제목</option>
			<option value="name">작성자</option>
			<option value="created">날짜</option>
		</select>
	</div>
	<input type="text" class="form-control" name="value" id="value">
	<div class="input-group-append">
		<button type="button" class="btn btn-dark btn-sm" onclick="search();">검색</button>
	</div>
</div>

<div class="btn_board">
	<button type="button" class="btn btn-dark btn-sm" onclick="javascript:location.href='<%=cp%>/nBoard/list'">새로고침</button>
	<button type="button" class="btn btn-dark btn-sm" onclick="javascript:location.href='<%=cp%>/nBoard/created'">글등록</button>
</div>

<div>
	${paging } 
</div>


<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>


<script type="text/javascript">
$(function readArticle(){
	$(".tr_data").click(function(){
		var num = $(this).attr("data-num");
		var url = "${article_url}"+"&num="+num;

		location.href=url;
	});
	
})
</script>
<script type="text/javascript">
function search(){
	var condition = $("#condition option:selected").val();
	var value = $("#value").val();
	var url = "<%=cp%>/nBoard/list?condition="+condition+"&value="+value;
	location.href=url;
}
</script>








