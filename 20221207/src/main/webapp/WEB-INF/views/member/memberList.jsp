<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기는 뭐지</title>
</head>
<!-- request객체에서members로 날아옴 ,members를 m으로 읽겠다 -->
<body>
	<div align="center">
		<br>
		<div><h1>회원목록</h1></div>
		<br>
		<div>
			<table border="1">
				<thead>
					<tr>
						<th width="150" align="center">아이디</th>
						<th width="150" align="center">이 름</th>
						<th width="150" align="center">나 이</th>
						<th width="150" align="center">전화번호</th>
						<th width="150" align="center">주 소</th>
						<th width="150" align="center">권 한</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach items="${members }" var="m">
					<tr onmouseover="this.style.background='#fcecae'"
						onmouseleave="this.style.background='#FFFFFF'"
						onclick="memberSelect('${m.memberId}')">
						<td width="150" align="center">${m.memberId }</td>
						<td width="150" align="center">${m.memberName }</td>
						<td width="150" align="center">${m.memberAge }</td>
						<td width="150" align="center">${m.memberTell }</td>
						<td width="150" align="center">${m.memberAddress }</td>
						<td width="150" align="center">${m.memberAuthor }</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div><br>
		<!--포스트 방식으로 데이터를 넘기기 위해 키값을 주기위한 히든폼 
		memberSelect(${m.memberId})에서 넘어온 값을 
		폼에 담고 -->
		<div>
		<form id="frm" action="memberSelect.do" method="post">
		<input type="hidden" id="memberId" name="memberId">
		</form>
		</div>
	</div>
	<!-- frm을 서밋하면 memberSelect.do 얘가 실행-->
<script type="text/javascript">
	function memberSelect(id) {
		document.getElementById("memberId").value=id;
		frm.submit(); // 숨겨진 폼을 전송.
		
	}
</script>	
	
</body>
</html>