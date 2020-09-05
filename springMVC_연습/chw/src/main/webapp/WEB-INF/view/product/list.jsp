<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String cp = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Atoverflow Product</title>
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>


<div class="container">
	<span>상품정보</span>
	<table class="table productTable">
	  <thead>
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">상품명</th>
	      <th scope="col">상품가격</th>
	      <th scope="col">상품사진</th>
	      <th scope="col">카트</th>
	    </tr>
	  </thead>
	  
	  <tbody class="productContents">
	  <c:forEach var="map" items="${listMap}" >
	    <tr data-id="${map.ID }">
	      <th scope="row">${map.ID }</th>
	      <td>${map.ITEM_NAME }</td>
	      <td class="price">
	     	 <fmt:formatNumber value="${map.ITEM_PRICE }" pattern="#,###"/>원
	      </td>
	      <td>
	      	<img src="http://svt.atoverflow.com:9999/static/${map.ITEM_IMAGE_URL }.jpg" style="height: 50px">
	      </td>
	      <td>
		      <input class="check_cart" type="checkbox" data-checked="0">카트에 넣기
	      </td>
	    </tr>
	    </c:forEach>
	  </tbody>
	  
	</table>
	<div>
		<button class="orderbtn" onclick="order();">주문</button>
	</div>
</div>

<br>
<br>

<div class="container orderProduct">
</div>
</body>

<script type="text/javascript">
var check_id_list = new Array;

// 체크한 상품 체크리스트에 담기
$(".check_cart").on("change", function checkList(){
	if($(this).attr("data-checked")==='0'){
		$(this).attr("data-checked", 1);
		
		$tr = $(this).closest("tr");
		$td = $tr.children();
		id = $td.eq(0).text();
		
		check_id_list.push(id);
	} else {
		$(this).attr("data-checked", 0);
		
		$tr = $(this).closest("tr");
		$td = $tr.children();
		id = $td.eq(0).text();
		
		check_id_list.forEach(function(item, index, array){
			if(item==id){
				var pos = check_id_list.indexOf(id);
				check_id_list.splice(pos, 1);
			}
		});
	}
});

//주문버튼 누르면 체크리스트의 품목 주문내역서에 출력
function order(){
	var url = "<%=cp%>/order";
	var data = { "idList" : check_id_list };

	$.ajax({
		url : url,
		data : data,
		type: "post",
		dataType: "html",
		success: function(html){
			$(".orderProduct").html(html);
		}
	});
};
</script>
</html>