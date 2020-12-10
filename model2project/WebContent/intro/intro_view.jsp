<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.Intro, vo.Member, java.util.*, java.text.SimpleDateFormat"  %>
<%
	Intro article = (Intro)request.getAttribute("article");
	Member articlem = (Member)request.getAttribute("articlem");
	String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
<title>강사소개 Detail</title>
<style>
	body,h1,h2,h3,h4,h5,h6,p,address,header,footer,section,aside,nav,ul,ol,li,dl,dt,dd,input,textarea,select,button {
    	margin: 0;
    	padding: 0;
    	font-family: 'Malgun Gothic',sans-serif;
    	font-size: 14px;
    	color: #222328;
	}
	
	body {
	    background-image: url(https://roman-flossler.github.io/StoryShowGallery/img/bg.png);
	}
	
	li {
   		list-style: none;
	}

	.clearfix {
		*zoom: 1;
	}
	.slider{
		width: 1200px;
		height: 300px; 
		overflow: hidden;
		margin: 0 auto;
	}
	
	h2 {
		text-align: center;
	}
	
	#basicInfoArea {
		height: 40px;
		text-align: center;
	}
	
	#articleContentArea {
		background: orange;
		margin-top: 20px;
		height: 350px;
		text-align: center;
		overflow: auto;
	}
	
	#commandList {
		margin: auto;
		width: 500px;
		text-align: center;
	}
	
	#container {
    width: 1200px;
    margin: 10px auto 0;
}
#container .notice {
    position: relative;
    float: left;
    width: 800px;
    margin-right: 15px;
}
#container .notice1 {
    position: relative;
    float: left;
    width: 355px;
    margin-right: 15px;
}
#container .notice h4 {
    position: relative;
    z-index: 1;
    float: left;
    height: 35px;
    padding: 0 12px;
    border: 1px solid #ccc;
    background-color: #d7d7d7;
    font-size: 16px;
    font-weight: bold;
    color: #606060;
    line-height: 35px;
    cursor: pointer;
    box-sizing: border-box;
}
#container .notice h4.on {
    border-bottom-color: #fff;
    background-color: #fff;
}

#container .notice ul {

}
#container .notice .news ul {
    padding-top: 20px;
}
#container .notice .news ul li {
    overflow: hidden;
    margin: 0 10px;
    border-bottom: 1px solid #dbdbdb;
    line-height: 30px;
}
#container .notice .news ul li a {
    float: left;
}
#container .notice .news ul li span {
    float: right;
    color: #606060;
}
#container .notice .gallery ul {
    display: none;
    padding: 20px 0 0 12px;
}
#container .notice .gallery ul li {
    float: left;
    margin-right: 13px;
}
#container .notice .gallery ul li.last {
    margin-right: 0;
}

#container .notice2 {
    position: relative;
    float: left;
    width: 1165px;
    margin-right: 15px;
    margin-top: 20px;
}
#container .notice2 h4 {
    position: relative;
    z-index: 1;
    float: left;
    height: 35px;
    padding: 0 12px;
    border: 1px solid #ccc;
    background-color: #d7d7d7;
    font-size: 16px;
    font-weight: bold;
    color: #606060;
    line-height: 35px;
    cursor: pointer;
    box-sizing: border-box;
}
#container .notice2 h4.on {
    border-bottom-color: #fff;
    background-color: #fff;
}

#container .notice2 ul {

}
#container .notice2 .news ul {
    padding-top: 20px;
}
#container .notice2 .news ul li {
    overflow: hidden;
    margin: 0 10px;
    border-bottom: 1px solid #dbdbdb;
    line-height: 30px;
}
#container .notice2 .news ul li a {
    float: left;
}
#container .notice2 .news ul li span {
    float: right;
    color: #606060;
}
#container .notice2 .gallery ul {
    display: none;
    padding: 20px 0 0 12px;
}
#container .notice2 .gallery ul li {
    float: left;
    margin-right: 13px;
}
#container .notice2 .gallery ul li.last {
    margin-right: 0;
}	
</style>
</head>
<body>
	<!-- 게시판 수정 -->
  	<section id="container" class="clearfix">
		<section class="notice1">
			<a href ="#"><img src="/upload/<%=article.getImg1() %>" alt="-" 
			style="width : 355px; height:300px; align:center;"/><span></span></a>					
		</section>
		<section class="notice">
			<div class="news">
				<ul>
					<li><a href="#" >교사이름</a><span>d</span></li>
					<li><a href="#">이메일</a> <span>s</span></li>
					<li><a href="#">성별</a> <span>a</span></li>
					<li><a href="#">전공</a> <span>a</span></li>
					<li><a href="#">학력</a> <span>a</span></li>
				</ul>				
			</div>
		</section>
			<section class="notice2">
				<div class="news">
					<h4 class="on">리뷰</h4>
					<ul>
						<li><a href="#">Site Usage Rules</a> <span>2020.12.01</span></li>
						<li><a href="#">We're looking for a new teacher with 2LW!</a> <span>2020.10.17</span></li>
						<li><a href="#">Site Changes in September</a> <span>2020.09.21</span></li>
						<li><a href="#">information on enrollment</a> <span>2020.09.12</span></li>
					</ul>				
				</div>
			</section>
<!-- 		<section class="notice">	
			<div id="news">		
				<ul>
					<li>내용 : <%=article.getContents() %></li>
				</ul>	
			</div>
		</section> -->
		
		
<!-- 교사본인에게만 보이는 탭( -->	
<!-- 		<section id="commandList">
			<a href="introReplyForm.do?intro_num=<%=article.getIntro_num() %>&page=<%=nowPage %>">[답변]</a>
			<a href="introModifyForm.do?intro_num=<%=article.getIntro_num() %>&page=<%=nowPage %>">[수정]</a>
			<a href="introDeleteForm.do?intro_num=<%=article.getIntro_num() %>&page=<%=nowPage %>">[삭제]</a> -->
<!-- )교사본인에게만 보이는 탭 -->	
<!-- 		<button type="button" class="btn btn-success" onclick="history.back(-1);">목록으로</button> -->	
	</section>
	
</body>
</html>