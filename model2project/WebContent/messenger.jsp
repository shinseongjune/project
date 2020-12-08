<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Message, vo.Review, java.util.ArrayList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	int nowPageNumber = 1;
	int pageCount = 5;
	int range = 5;
	String prevDisabled = "";
	String nextDisabled = "";
%>
<html lang="ko">
<head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
	<title>2LW</title>
	<link rel="stylesheet" href="css/sidebar.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/messenger.css">
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
					<li class="nav-item"><a class="nav-link" href="#">강사소개
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#carrer">강의목록</a></li>
					<li class="nav-item active"><a class="nav-link" href="editProfilePage.do">마이페이지
							<span class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link" href="faq.html">고객센터</a></li>
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
<%
	if(loginMember == null){
		out.println("<script>alert('로그인이 필요합니다.');location.href='login.jsp';</script>");
	} else {
				int lastPage = (int)session.getAttribute("messageLastPage");
				if (request.getParameter("page") != null) nowPageNumber = Integer.parseInt(request.getParameter("page"));
				if (nowPageNumber < 1) nowPageNumber = 1;
				if (nowPageNumber > lastPage) nowPageNumber = lastPage;
				ArrayList[] messageList = (ArrayList[])session.getAttribute("messageList");
				int startNumber = (((nowPageNumber - 1) / pageCount) * range) + 1;
				int endNumber = startNumber + range - 1;
				if (nowPageNumber <= 1) {
					prevDisabled = " disabled";
				}
				if (nowPageNumber >= lastPage) {
					nextDisabled = " disabled";
				}
%>
	<div class="editcont">
		<div class="sidebar">
			<div class="bigMyPage">My Page</div>
			<div>
				<div class="myPageMenu"><a href="editProfilePage.do"><img src="images/user_icon.png">&nbsp;개인정보 수정</a></div>
				<div class="myPageMenu"><a href="favorites.do"><img src="images/heart_icon.png">&nbsp;즐겨찾기 목록</a></div>
				<div class="myPageMenu"><a href="review.do"><img src="images/star_icon.png">&nbsp;리뷰남기기</a></div>
				<div class="myPageMenu on"><a href="messenger.do"><img src="images/mail_icon.png">&nbsp;쪽지함</a></div>
				<div class="myPageMenu"><a href="quit.jsp"><img src="images/x_mark_icon.png">&nbsp;회원 탈퇴</a></div>
			</div>
		</div>
			<div class="contents">
				<div class="row justify-content-center">
					<div class="col-md-12">
						<div class="bbsWrapper">
							<ul class="bbsWrapperList">
								<li class="bbsHeader">
									<ul class="bbsHeaderContents">
										<li class="bbsTitleHeader">MESSAGE</li>
										<li class="bbsWriterHeader">SENDER</li>
										<li class="bbsDeleteHeader">DELETE</li>
									</ul>
								</li>
<%
				ArrayList<Message> mesList = messageList[0];
				ArrayList<Member> memList = messageList[1];
				
				for (int i = 0; i < mesList.size();i++) {
%>
								<li class="bbsBody">
									<ul class="bbsBodyContents">
										<li class="bbsTitle">
											<div><a class="messagePopupWindow"><%=mesList.get(i).getTitle() %></a></div>
											<div class="bbsTitleDetail"><%=mesList.get(i).getTime() %></div>
											<div class="messagePopup">
												<h3><%=mesList.get(i).getTitle() %></h3>
												<div class="messageContents">
													<p><%=mesList.get(i).getContents() %></p>			
												</div>
												<p class="close"><button type="button" class="btn btn-secondary">X</button></p>
											</div>
										</li>
										<li class="bbsWriter"><%=memList.get(i).getName() %></li>
										<li class="messageDeleteButton"><input type="button" class="btn btn-danger" value="삭제" onclick="location.href='messageDelete.do?message_num=<%=mesList.get(i).getMessage_num() %>'"/></li>
									</ul>
								</li>
								
<%
				}
%>
							</ul>
						</div>
					</div>
						
						<nav aria-label="Page navigation example">
						  <ul class="pagination justify-content-center">
						    <li class="page-item<%=prevDisabled %>">
						      <a class="page-link" href="messenger.do?page=<%=nowPageNumber - 1 %>" tabindex="-1">Previous</a>
						    </li>
<%
				for (int i = startNumber; i <= Math.min(endNumber, lastPage); i++) {					    
%>
						    <li class="page-item"><a class="page-link" href="messenger.do?page=<%=i%>"><%=i %></a></li>
<%
				}
%>
						    <li class="page-item<%=nextDisabled%>">
						      <a class="page-link" href="messenger.do?page=<%=nowPageNumber + 1 %>">Next</a>
						    </li>
						  </ul>
						</nav>
						
				</div>			        
						<div class="float-right">
							<button class="btn btn-primary" onClick="location.href='sendMessage.jsp'">쪽지 보내기</button>
							<button class="btn btn-primary" onClick="location.href='myMessage.do?page=1'">보낸 쪽지함</button>
						</div>
			</div>
	</div>
	
<%
	}
%>
	<script src="./js/jquery-1.12.4.min.js"></script>
	<!-- Optional JavaScript; -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
	<script src="js/messenger.js"></script>
</body>
</html>