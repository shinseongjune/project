<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.Member, vo.Lecture, java.util.LinkedList" %>
<!DOCTYPE html>
<%
	LinkedList<Object> lectureList = (LinkedList<Object>)session.getAttribute("lectureList");
	Lecture lec = null;
	Member mem = null;
	if(lectureList != null){
		lec = (Lecture)lectureList.get(0);
		mem = (Member)lectureList.get(1);
	}
%>
<html>
<head>
<meta charset="UTF-8">
<title>pay</title>
<style>
body {
	width: 580px;
	height: 625px;
	border: 1px solid black;
	margin: auto;
}
.main {
	width: 550px;
	height: 90px;
	border: 1px solid black;
	margin: auto;
}
button {
    width:100px;
    border-radius:10px;
    border: none;
    padding: 15px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    cursor: pointer;
}

.btn-primary {
	color:#fff;
	background-color:#007bff;
	border-color:#007bfff;
}

.btn-primary:hover {
	color:#fff;
	background-color:#0069d9;
	border-color:#0062cc
}

.btn-primary.focus,.btn-primary:focus {
	box-shadow:0 0 0 .2rem rgba(0,123,255,.5)
}

.btn-primary.disabled,.btn-primary:disabled {
	background-color:#007bff;
	border-color:#007bff
}

.btn-success {
	color:#fff;
	background-color:#28a745;
	border-color:#28a745;
}
	
.btn-success:hover {
	color:#fff;
	background-color:#218838;
	border-color:#1e7e34
}

.btn-success.focus,.btn-success:focus {
	box-shadow:0 0 0 .2rem rgba(40,167,69,.5)
}

.btn-success.disabled,.btn-success:disabled {
	background-color:#28a745;border-color:#28a745
}

.btn {
	float: right;
	margin-top: 75px;
}
</style>
</head>
<body onresize="parent.resizeTo(600,700)" onload="parent.resizeTo(600,700)">
<h1 style="text-align: center;">결제화면</h1>
<form action="doPay.do" method="post" name="introform">
<section class="main">
	<a>강의번호 : </a><span><%=lec.getLecture_num() %></span><br>
	<a>강의명 : </a><span><%=lec.getLecture_title() %></span><br>
	<a>교사명 : </a><span><%=mem.getName() %></span><br>
	<a>결제가격 : </a><span><%=lec.getPrice() %></span>
</section>
<input type="hidden" name="lecture_num" value="<%=lec.getLecture_num() %>" />
<label class="reg" for="class">
	<br><select size="1" id="class" style="height: 30px; margin-left: 15px;">
		<option class="cardTab" value="card">카드</option>
		<option class="bankingTab" value="banking">계좌이체</option>
		<option class="phoneTab" value="phone">휴대폰</option>
	</select>
</label>
<fieldset class="cardForm">
	<legend>카드선택</legend>
	 신한<input type="radio" name="type" value="신한카드" />
	  현대<input type="radio" name="type" value="현대카드" />
	  하나<input type="radio" name="type" value="하나카드" />
	  우리<input type="radio" name="type" value="우리카드" />
	  KB국민<input type="radio" name="type" value="KB국민카드" />
	  IBK기업<input type="radio" name="type" value="IBK기업카드" />
</fieldset>
<fieldset class="bankingForm">
</fieldset>
<fieldset class="phoneForm">
</fieldset>
<fieldset>
	<legend>정보 입력</legend>
<div class="cardCode">
	<a>카드번호</a><br><textarea style="height: 25px; width: 400px; resize: none; font-size: 20px;" name="pay_code" placeholder="-없이 입력" required="required"></textarea><br>
</div>
<div class="bankingCode">
</div>	
<div class="phoneCode">
</div>		
</fieldset>
<div class="btn">
	<button type="button" class="btn btn-success" id="closeButton" onclick="window.close();" style="margin-right: 185px;">결제취소</button>
	<button type="button" class="btn btn-primary" id="submitButton" style="margin-right: 10px;">결제하기</button>
</div>
</form>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(function(){
	$(".bankingForm").hide();
	$(".phoneForm").hide();
	$(".bankingCode").hide();
	$(".phoneCode").hide();
	$("#class").change(function(){
		let select = $(this).val();
		if(select == "card") {
			$(".cardForm").html('<legend>카드선택</legend>신한<input type="radio" name="type" value="신한카드" />현대<input type="radio" name="type" value="현대카드" />하나<input type="radio" name="type" value="하나카드" />우리<input type="radio" name="type" value="우리카드" />KB국민<input type="radio" name="type" value="KB국민카드" />IBK기업<input type="radio" name="type" value="IBK기업카드" />');
			$(".cardForm").show();
			$(".bankingForm").empty();
			$(".bankingForm").hide();
			$(".phoneForm").empty();
			$(".phoneForm").hide();
			$(".cardCode").html('<a>카드번호</a><br><textarea style="height: 25px; width: 400px; resize: none; font-size: 20px;" name="pay_code" placeholder="-없이 입력" required="required"></textarea><br>');
			$(".cardCode").show();
			$(".bankingCode").empty();
			$(".bankingCode").hide();
			$(".phoneCode").empty();
			$(".phoneCode").hide();
		} else if(select == "banking") {
			$(".cardForm").empty();
			$(".cardForm").hide();
			$(".bankingForm").html('<legend>은행선택</legend>신한<input type="radio" name="type" value="신한은행" />하나<input type="radio" name="type" value="하나은행" />우리<input type="radio" name="type" value="우리은행" />KB국민<input type="radio" name="type" value="KB국민" />IBK기업<input type="radio" name="type" value="IBK기업은행" />카카오뱅크<input type="radio" name="type" value="카카오뱅크" />');
			$(".bankingForm").show();
			$(".phoneForm").empty();
			$(".phoneForm").hide();
			$(".cardCode").empty();
			$(".cardCode").hide();
			$(".bankingCode").html('<a>계좌번호</a><br><textarea style="height: 25px; width: 400px; resize: none; font-size: 20px;" name="pay_code" readonly>신한은행 150-248-945238</textarea><br>');
			$(".bankingCode").show();
			$(".phoneCode").empty();
			$(".phoneCode").hide();
		} else {
			$(".cardForm").empty();
			$(".cardForm").hide();
			$(".bankingForm").empty();
			$(".bankingForm").hide();
			$(".phoneForm").html('<legend>통신사선택</legend>SKT<input type="radio" name="type" value="SKT" />KT<input type="radio" name="type" value="KT" />LG U+<input type="radio" name="type" value="LG U+" />알뜰폰<input type="radio" name="type" value="알뜰폰" />');
			$(".phoneForm").show();
			$(".cardCode").empty();
			$(".cardCode").hide();
			$(".bankingCode").empty();
			$(".bankingCode").hide();
			$(".phoneCode").html('<a>전화번호</a><br><textarea style="height: 25px; width: 400px; resize: none; font-size: 20px;" name="pay_code" placeholder="-없이 입력" required="required"></textarea><br>');
			$(".phoneCode").show();
		}
	});
	$("#submitButton").click(function(){
		if($("input:radio[name='type']").is(":checked")) {
			if($("textarea[name='pay_code']").val().length != 0) {
				document.introform.submit();
			} else {
				alert("필수 항목을 입력해주세요.")
			}
		} else {
			alert("은행 또는 통신사를 선택해주세요.");
		}
	});
	$(document).on("keydown", "textarea[name='pay_code']", function(e) {
		if(!((e.which >= 37 && e.which <= 40) || e.which == 46 || e.which == 8 || e.which == 0 || (e.which >= 48 && e.which <= 57) || (e.which >= 96 && e.which <= 105))) {
			return false;
		}
	});
	$(document).on("paste", "textarea[name='pay_code']", function(){
		return false;
	});
});
</script>
</body>
</html>