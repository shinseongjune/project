<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Free, java.util.LinkedList" %>
<!DOCTYPE html>
<%
// 	Member loginMember = (Member) session.getAttribute("loginMember");
// 	int nowPageNumber = 1;
// 	int pageCount = 5;
// 	int range = 5;
// 	String prevDisabled = "";
// 	String nextDisabled = "";
%>
<html lang="ko">
<head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
	<title>2LW</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-sm navbar-dark bg-dark fixed-top">
			<div class="container">
				<a class="navbar-brand" href="index.jsp">2LW</a>	
			</div>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
	
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="introList.do">강사소개
					</a></li>
					<li class="nav-item"><a class="nav-link" href="lectureList.do">강의목록</a></li>
					<li class="nav-item"><a class="nav-link" href="editProfilePage.do">마이페이지</a></li>
					<li class="nav-item"><a class="nav-link" href="faq.do">고객센터</a></li>
				</ul>
				<form class="form-inline my-2 my-lg-0">
					<input class="form-control mr-sm-2" type="search"
						placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
			</div>
		</nav>
	</header>
		<!-- //header -->
<div class="container" id="main">
<%
// 	if(loginMember == null){
// 		out.println("<script>alert('로그인이 필요합니다.');location.href='loginPage.do';</script>");
// 	} else {
// 				int lastPage = 1;
// 				if (session.getAttribute("lastPage") != null) lastPage = (int) session.getAttribute("lastPage");
// 				if (request.getParameter("page") != null) nowPageNumber = Integer.parseInt(request.getParameter("page"));
// 				if (nowPageNumber < 1) nowPageNumber = 1;
// 				if (nowPageNumber > lastPage) nowPageNumber = lastPage;
// 				LinkedList[] freeList = (LinkedList[])session.getAttribute("freeList");
// 				int startNumber = (nowPageNumber - 1) / pageCount * range + 1;
// 				int endNumber = startNumber + range - 1;
// 				if (nowPageNumber == 1) {
// 					prevDisabled = " disabled";
// 				}
// 				if (nowPageNumber == lastPage) {
// 					nextDisabled = " disabled";
// 				}
%>
	<div class="container" style="width:1200px;">
		<div></div> <!-- 카테고리 div -->
		
		<div class="row">
			<div class="col-12 d-flex flex-wrap  justify-content-between">
				
				<div class="card my-2" style="width: 18rem;">
				  <img src="images/star_icon.png" class="card-img-top" alt="...">
				  <div class="card-body">
				    <h5 class="card-title">강의 제목</h5>
				    <p class="card-text">강의 설명</p>
				    <a href="#" class="btn btn-primary">강의 보기</a>
				  </div>
				</div>
				
			</div>
		</div>
	</div>
	
<%
// 	}
%>
</div>
	<script src="./js/jquery-1.12.4.min.js"></script>
	<!-- Optional JavaScript; -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
	<script>
		$(function(){
			$("#main").css("margin-top", $("nav").outerHeight(true) + "px");
			$(window).resize(function(){
				$("#main").css("margin-top", $("nav").outerHeight(true) + "px");
			});
		});
	</script>
</body>
</html>