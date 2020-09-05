<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
   String cp = request.getContextPath();
%>

<!DOCTYPE html>

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>IamHC</title>

  <!-- Bootstrap core CSS -->
  <link href="<%=cp %>/sidebar/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="<%=cp %>/sidebar/css/simple-sidebar.css" rel="stylesheet">

</head>

<body>

  <div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">
      	<a href="<%=cp%>" style="text-decoration: none;">Board Mastery</a>
      </div>
      <div class="list-group list-group-flush">
        <a href="<%=cp %>/nBoard/list" class="list-group-item list-group-item-action bg-light">일반게시판</a>
        <a href="<%=cp %>/aboard/list" class="list-group-item list-group-item-action bg-light">답변게시판</a>
        <a href="<%=cp %>/mfboard/list" class="list-group-item list-group-item-action bg-light">다중파일 업로드</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">다중검색게시판_sort Jquery</a>
      </div>
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: black;
    margin-left: 5px;">
                방명록
       </a>
       <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
	        <a href="#" class="list-group-item list-group-item-action bg-light">JavaScript_text(html)</a>
	        <a href="#" class="list-group-item list-group-item-action bg-light">JavaScript_xml</a>
	        <a href="#" class="list-group-item list-group-item-action bg-light">Jquery_text(html)</a>
	        <a href="#" class="list-group-item list-group-item-action bg-light">Jquery_json(ResponseBody)</a>
	        <a href="#" class="list-group-item list-group-item-action bg-light">Jquery_xml(ResponseBody)</a>
	        <a href="#" class="list-group-item list-group-item-action bg-light">Jquery_json(JSONObject)</a>
	        <a href="#" class="list-group-item list-group-item-action bg-light">Jquery_xml</a>  
       </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

      <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
        <button class="btn btn-primary" id="menu-toggle">Toggle Menu</button>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
          	<c:if test="${empty sessionScope.member }">
	            <li class="nav-item active">
	              <a class="nav-link" href="<%=cp%>/member/created">회원가입 <span class="sr-only">(current)</span></a>
	            </li>
	            <li class="nav-item active">
	              <a class="nav-link" href="<%=cp%>/member/login">로그인</a>
	            </li>
            </c:if>
            <c:if test="${not empty sessionScope.member }">
        		<li class="nav-item active">
	              <a class="nav-link" href="<%=cp%>/member/memberInfo">
	              <span style="color: blue; font-weight: 800;">${sessionScope.member.name }</span>님 <span class="sr-only">(current)</span></a>
	            </li>
	            <li class="nav-item active">
	              <a class="nav-link" href="<%=cp%>/member/logout">로그아웃</a>
	            </li>
            </c:if>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Dropdown
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="<%=cp%>/member/memberInfo">회원정보</a>
                <a class="dropdown-item" href="#">쪽지</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">E-Mail</a>
                
              </div>
            </li>
          </ul>
        </div>
      </nav>
      
      
      
      
      
      
	<tiles:insertAttribute name="body"/>






  </div>
  <!-- /#wrapper -->

  <!-- Bootstrap core JavaScript -->
  <script src="<%=cp %>/sidebar/vendor/jquery/jquery.min.js"></script>
  <script src="<%=cp %>/sidebar/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Menu Toggle Script -->
  <script>
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>
  
</div>
</body>
