<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="vo.Intro, vo.Member, java.util.*, java.text.SimpleDateFormat"%>
<%
	ArrayList[] articleList = (ArrayList[]) session.getAttribute("articleList");
	ArrayList<Intro> intList = articleList[0];
	ArrayList<Member> memList = articleList[1];
	Intro article = intList.get(0);
	Member articlem = memList.get(0);
	String nowPage = request.getParameter("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='./css/bootstrap.css' type='text/css' media='all' />
<link rel='stylesheet' href='./css/imodywrit.css' type='text/css' media='all' />
<title>강사소개 수정</title>
</head>
<body>
	<section class="container">
		<form method="post" enctype="multipart/form-data" action="introModifyPro.do?intro_num=<%=article.getIntro_num() %>&page=<%=nowPage %>">
			<section>
				<ul>
					<li style="margin-top: 10px;"><input name="img1" type="file"
						id="img1" required="required" /> <input type="text"
						class="inputSlot" name="imgex1" placeholder="사진 소개1"
						required="required" /></li>
					<li style="margin-top: 4px;"><input name="img2" type="file"
						id="img2" /> <input type="text" class="inputSlot" name="imgex2"
						placeholder="사진 소개2" /></li>
					<li style="margin-top: 4px;"><input name="img3" type="file"
						id="img3" /> <input type="text" class="inputSlot" name="imgex3"
						placeholder="사진 소개3" /></li>
					<li style="margin-top: 4px;"><input name="img4" type="file"
						id="img4" /> <input type="text" class="inputSlot" name="imgex4"
						placeholder="사진 소개4" /></li>
					<li style="margin-top: 4px;"><input name="img5" type="file"
						id="img5" /> <input type="text" class="inputSlot" name="imgex5"
						placeholder="사진 소개5" /></li>
					<li style="margin-top: 4px;"><input name="img6" type="file"
						id="img6" /> <input type="text" class="inputSlot" name="imgex6"
						placeholder="사진 소개6" /></li>
				</ul>
				<div>
					<ul>
						<li><input type="text" class="inputSlot" name="contents"
							id="contents" placeholder="<%=article.getContents()%>"
							style="width: 439px; height: 25px;" /></li>
						<li>
							<input type="submit"
							style="margin-top: 20px; margin-left: 119px;"
							class="btn btn-primary" value="수정하기" />
							<button
								style="margin-top: 20px; margin-left: 15px;"
								type="button" class="btn btn-success"
								onclick="history.back(-1);">돌아가기</button> 
						</li>
					</ul>
				</div>
			</section>
		</form>
	</section>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
</body>
</html>