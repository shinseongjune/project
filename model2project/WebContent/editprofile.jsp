<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member" %>
<!DOCTYPE html>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
%>
<html lang="ko">
<head>
	<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
	<title>2LW</title>
	<link rel="stylesheet" href="css/sidebar.css">
	<link rel="stylesheet" href="./css/editprofile.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<%@ include file="navbar.html" %>
<%
	if(loginMember == null){
		out.println("<script>alert('로그인이 필요합니다.');location.href='login.jsp';</script>");
	} else {
%>
	<div class="editcont">
			<%@ include file="mysidebar.html" %>
			<div class="contents">
				<div class="bigEdit">Edit Profile</div>
				<form action="editProfile.do" method="post">
					<input type="hidden" name="id" value="<%=(String)loginMember.getId() %>">
					<div class="inputSlot">
						Password : <br />
						<input type="password" name="password" required="required" autocomplete="off" />
					</div>
					<div class="inputSlot">
						Name : <br />
						<input type="text" name="name" value="<%=loginMember.getName() %>" required="required" autocomplete="off" />
					</div>
					<div class="inputSlot">
						Email : <br />
						<input type="text" name="email" value="<%=loginMember.getEmail() %>" required="required" autocomplete="off" />
					</div>
					<div class="inputSlot">
						Gender :
							<div class="btn-group btn-group-toggle" data-toggle="buttons">
							  <label class="btn btn-secondary">
							    <input type="radio" name="gender" value="남" <% if(loginMember.getGender().equals("남")) { %>checked<% } %> /> 남
							  </label>
							  <label class="btn btn-secondary">
							    <input type="radio" name="gender" value="여" <% if(loginMember.getGender().equals("여")) { %>checked<% } %> /> 여
							  </label>
							</div>
					</div>
<%
						if (loginMember.getClassify().equals("교사")) {
%>
					<div class="inputSlot">
						Major : <br />
						<input type="text" name="major" value="<%=loginMember.getMajor() %>" required="required" autocomplete="off" />
					</div>
					<div class="inputSlot">
						Education : <br />
						<input type="text" name="education" value="<%=loginMember.getEducation() %>" required="required" autocomplete="off" />
					</div>
<%
						}
%>
					<div class="submitButton">
						<input type="submit" value="개인정보 수정" />
					</div>
				</form>
			</div>
	</div>
<%
	}
%>
	<script src="./js/jquery-1.12.4.min.js"></script>
	<script src="./js/editprofile.js"></script>
	<!-- Optional JavaScript; -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>
</html>