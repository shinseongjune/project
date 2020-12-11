<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.PageInfo, vo.Intro, vo.Member, java.util.*, java.text.SimpleDateFormat" %>
<%
	LinkedList<Intro> articleList = (LinkedList<Intro>)request.getAttribute("articleList");
	Member loginMember = (Member) session.getAttribute("loginMember");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
 		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
		<title>강사소개</title>
<style>
body {
	background-color: #d7e8e9;
}
</style>
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
				<li class="nav-item active"><a class="nav-link" href="introList.do">강사소개
					<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#carrer">강의목록</a></li>
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
	<div class="container" id="main">
<%
	if(loginMember == null){
		out.println("<script>alert('로그인이 필요합니다.');location.href='login.jsp';</script>");
	} else {
%>
		<table>
<%
	}
%>		
<%
	if(articleList != null && listCount > 0) {
%>
			<tbody>
				<tr>
<%
	for(int i=0;i<articleList.size();i++) {
%>
					<td style="text-align: center;">
						<img src="/upload/<%=articleList.get(i).getImg1() %>" alt="-" 
						style="width : 355px; height:300px; align:center;"/>
						<br><a href="introDetail.do?intro_num=<%=articleList.get(i).getIntro_num() %>&page=<%=nowPage %>">
						<%=articleList.get(i).getContents() %>
						</a>
					</td>
<%
	if(i%3==2) {
		out.println("</tr><tr>");						
		}
	} 
%>
				</tr>
			</tbody>
		</table>
<%
	}else{
%>
		<div class="container" style="text-align: center;"><h2>등록된 소개가 없습니다.</h2></div>
<%
	}
%>
	<nav aria-label="Page navigation example">
		<ul class="pagination pagination-sm justify-content-center">
<%
	if (nowPage <= 1) {
%>
	    	<li class="page-item disabled"><a class="page-link" href="introList.do?page=<%=nowPage-1 %>">Previous</a></li>
<%
	}else{
%>
	  	 	 <li class="page-item"><a class="page-link" href="introList.do?page=<%=nowPage-1 %>">Previous</a></li>
<%
	}for(int a = startPage;a<=endPage;a++){
		if(a==nowPage){
%>
	    	<li class="page-item active"><a class="page-link" href="introList.do?page=<%=a %>"><%=a %></a></li>
<%
	}else{
%>
			<li class="page-item"><a class="page-link" href="introList.do?page=<%=a %>"><%=a %></a></li>
<%
	}
}	
	if(nowPage>=endPage) {
%>
		    <li class="page-item disabled"><a class="page-link" href="introList.do?page=<%=nowPage+1 %>">Next</a></li>
<%
	}else{
%>
	 	   <li class="page-item"><a class="page-link" href="introList.do?page=<%=nowPage+1 %>">Next</a></li>
<%
	}
%>	    
		</ul>
	</nav>
	<div class="row">
		<div class="ml-auto"><a href="introWriteForm.do"><input type="button" class="btn btn-primary" value="작성하기"/></a></div>
	</div>
	</div>	
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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