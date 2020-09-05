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
</head>


<body>
<div class="container orderProduct">
	<span>주문정보</span>
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">상품명</th>
	      <th scope="col">상품가격</th>
	      <th scope="col">개수</th>
	      <th scope="col">합계</th>
	    </tr>
	  </thead>
	  <tbody>
	  
	  <c:forEach var="map" items="${listMap}" >
	    <tr>
	      <th scope="row">${map.ID }</th>
	      <td class="name">${map.ITEM_NAME }</td>
	      <td><span class="price">${map.ITEM_PRICE }</span>원</td>
	      <td>
	      	<input name="product_cnt" value="1" style="width: 40px;">
	      	<button class="up_btn">▲</button>
	      	<button class="down_btn">▼</button>
	      </td>
	      <td>
	     	 <span class="sum" >${map.ITEM_PRICE }</span>원
	     </td>
	    </tr>
	    </c:forEach>
	     <tr>
	      <th scope="row">총계</th>
	      <td></td>
	      <td></td>
	      <td></td>
	      <td>
	     	<span class="total">${TOTAL }</span>원
	      </td>
	    </tr>
	  </tbody>
	</table>
</div>
</body>


<script type="text/javascript">
$(".up_btn").click(function upbtn(){
	var cnt = $(this).closest("tr").find("input").val();
	var price = $(this).closest("tr").find(".price").text();
	price = price.replace(/,/gi, '');

	var ex_sum = cnt*price;
	
	cnt = Number(cnt) + 1 ;

	var sum = cnt*price;
	
	var total = $(".total").text();
	total = total.replace(/,/gi, '');
	total *= 1;
	total += sum - ex_sum;
	
	$(this).closest("tr").find("input").val(cnt);
	$(this).closest("tr").find(".sum").text(comma(sum));
	$(".total").text(comma(total));
});

$(".down_btn").click(function downbtn(){
	var cnt = $(this).closest("tr").find("input").val();
	var price = $(this).closest("tr").find(".price").text();
	price = price.replace(/,/gi, '');

	var ex_sum = cnt*price;
	
	cnt = Number(cnt) -1 ;
	
	var sum = cnt*price;
	
	var total = $(".total").text();
	total = total.replace(/,/gi, '');
	total *= 1;
	total += sum - ex_sum;
	
	$(this).closest("tr").find("input").val(cnt);
	$(this).closest("tr").find(".sum").text(comma(sum));
	$(".total").text(comma(total));
});

function comma(num){ 
    var len, point, str; 
       
    num = num + ""; 
    point = num.length % 3 ;
    len = num.length; 
   
    str = num.substring(0, point); 
    while (point < len) { 
        if (str != "") {
        	str += ","; 
        }
        str += num.substring(point, point + 3); 
        point += 3; 
    } 
     
    return str;
};
</script>
</html>