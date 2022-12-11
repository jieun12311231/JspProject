<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/menu.css">
</head>
<!-- 아이디가 비어있으면 login페이지를 보여주고 아이디가 비어있지않으면 로그아웃 페이지를 보여줌 
어드민 계정만 멤버 볼수있게 >> 관리자, 유저 가 볼수있는 페이지 구분가능 ex) 어드민 : 배송, 회원들의 주문내역
// eq은 ==이랑 같음 ne은 != -->
<body>
	<nav id="topMenu">
		<ul>
			<li><a class="menuLink" href="main.do">Home</a></li>	
			<li><a class="menuLink" href="#">Notice</a></li>	
			<li><a class="menuLink" href="#">Content</a></li>
			<c:if test="${author eq 'ADMIN' }">
			<li><a class="menuLink" href="memberList.do">Member</a></li>
			</c:if>
			<c:if test="${empty id }">	
			<li><a class="menuLink" href="memberJoinForm.do">Join</a></li>
			</c:if>
			<c:if test="${empty id }">
			<li><a class="menuLink" href="memberLoginForm.do">Login</a></li>
			</c:if>
			<c:if test="${not empty id }">
			<li><a class="menuLink" href="memberLogout.do">Logout</a></li>
			</c:if>
		</ul>
	</nav>
</body>
</html>