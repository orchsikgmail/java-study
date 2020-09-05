<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   String cp = request.getContextPath();
%>

<div class="container-fluid" style="text-align: center">
    <img src="<%=cp%>/img/삭4.png"  style="display:block; margin: 10px auto; max-width: 250px; height: auto;">
    <c:if test="${empty msg }">
    <h1 class="mt-4">Do you wanna be a BoardMaster?</h1>
    </c:if>
    <c:if test="${not empty msg }">
    <h1 class="mt-4">${msg }</h1>
    </c:if>
</div>
