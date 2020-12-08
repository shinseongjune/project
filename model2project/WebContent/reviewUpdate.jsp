<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Lecture, vo.Review, java.util.ArrayList" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
%>
<html lang="ko">
<head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
	<title>2LW</title>
	<link rel="stylesheet" href="css/sidebar.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/review.css">
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
		Review re = (Review) session.getAttribute("re");
%>
	<div class="editcont">
		<div class="sidebar">
			<div class="bigMyPage">My Page</div>
			<div>
				<div class="myPageMenu"><a href="editProfilePage.do"><img src="images/user_icon.png">&nbsp;개인정보 수정</a></div>
				<div class="myPageMenu"><a href="favorites.do"><img src="images/heart_icon.png">&nbsp;즐겨찾기 목록</a></div>
				<div class="myPageMenu on"><a href="review.do"><img src="images/star_icon.png">&nbsp;리뷰남기기</a></div>
				<div class="myPageMenu"><a href="messenger.do"><img src="images/mail_icon.png">&nbsp;쪽지함</a></div>
				<div class="myPageMenu"><a href="quit.jsp"><img src="images/x_mark_icon.png">&nbsp;회원 탈퇴</a></div>
			</div>
		</div>
			<div class="contents">
			
				<form method="post" action="reviewUpdate.do">
					<div class="row justify-content-center mb-5">
						<div class="col-md-12">
							<div class="bbsWrapper">
								<ul class="bbsViewWrapperList">
									<li class="bbsViewWriter">
										<ul>
											<li class="bbsViewWriterHeader">WRITER</li>
											<li class="bbsViewWriterName"><%=loginMember.getName() %></li>
										</ul>
									</li>
									<li class="bbsViewTitle">
										<ul>
											<li class="bbsViewTitleHeader">TITLE</li>
											<li class="bbsViewTitleText"><input type="text" name="title" required="required" autocomplete="none" value="<%=re.getTitle() %>" autocomplete="off" /></li>
										</ul>
									</li>
									<li class="bbsViewBody">
										<div>
											<textarea cols="50" rows="10" style="resize: none;" name="contents" placeholder="500자까지 적을 수 있습니다."><%=re.getContents() %></textarea>
										</div>
									</li>
								</ul>
							</div>
						</div>
							
							
					</div>			        
						<input type="hidden" name="review_num" value="<%=re.getReview_num() %>" />
							<div class="float-right">
								<input type="submit" value="작성 완료" class="btn btn-primary" />
							</div>
							
				</form>
						
			</div>
	</div>
	
<%
	}
%>
	<script src="./js/jquery-1.12.4.min.js"></script>
	<!-- Optional JavaScript; -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
	<script>
      function confirmDelete() {
        if (window.confirm("정말 삭제하시겠습니까?")) {
          location.href="reviewDelete.do";
        }
      }
    </script>
</body>
</html>