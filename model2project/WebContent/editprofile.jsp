<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
	<title>2LW</title>
	<link rel="stylesheet" href="./css/editprofile.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-sm navbar-dark bg-dark fixed-top">
			<div class="container">
				<a class="navbar-brand" href="index.html">2LW</a>	
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
					<li class="nav-item active"><a class="nav-link" href="editProfile.do">마이페이지
							<span class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link" href="#contact">고객센터</a></li>
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
// 	Member member = (Member) session.getAttribute("loginMember");
	
// 	if(member == null){
// 		out.println("<script>alert('로그인이 필요합니다.');history.back();</script>");
// 	} else {
// 		String classify = member.getClassify();
%>
	<div class="editcont">
			<div class="sidebar">
				<div class="bigMyPage">My Page</div>
				<div>
					<div class="myPageMenu on"><a href="editProfile.do"><img src="images/user_icon.png">&nbsp;개인정보 수정</a></div>
					<div class="myPageMenu"><a href="#"><img src="images/heart_icon.png">&nbsp;즐겨찾기 목록</a></div>
					<div class="myPageMenu"><a href="#"><img src="images/star_icon.png">&nbsp;리뷰남기기</a></div>
					<div class="myPageMenu"><a href="#"><img src="images/mail_icon.png">&nbsp;쪽지함</a></div>
					<div class="myPageMenu"><a href="#"><img src="images/x_mark_icon.png">&nbsp;회원 탈퇴</a></div>
				</div>
			</div>
			<div class="contents">
				<div class="bigEdit">Edit Profile</div>
				<form action="#" method="post">
					<div class="inputSlot">
						Name : <br />
						<input type="text" name="name" required="required" autocomplete="off" />
					</div>
					<div class="inputSlot">
						Email : <br />
						<input type="text" name="email" required="required" autocomplete="off" />
					</div>
					<div class="inputSlot">
						Gender : <select name="Gender">
										<option value="남">Male</option>
										<option value="여">Female</option>
								 </select>
					</div>
<%
// 						if (classify == "교사") {
%>
					<div class="inputSlot">
						Major : <br />
						<input type="text" name="major" required="required" autocomplete="off" />
					</div>
					<div class="inputSlot">
						Education : <br />
						<input type="text" name="education" required="required" autocomplete="off" />
					</div>
<%
// 						}
%>
					<div class="submitButton">
						<input type="submit" value="개인정보 수정" />
					</div>
				</form>
			</div>
	</div>
<%
// 	}
%>
	<script src="./js/jquery-1.12.4.min.js"></script>
	<script src="./js/editprofile.js"></script>
	<!-- Optional JavaScript; -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>
</html>