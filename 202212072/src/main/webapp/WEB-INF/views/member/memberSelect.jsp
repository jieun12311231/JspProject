<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!--hidden폼으로 input태그의 value값으로 아이디를 넣어놓음 
memberEdit똑같은 메소드에서 전달인자를 다르게함 'E'는 수정'D'는 삭제 -->
<body>
	<div align="center">
	<br>
	<div><h1>회원 상세조회</h1></div>
	<br>
	<div>
	<table border="1">
	<thead>
	<tr>
	<th width="150" align="center">아이디</th>
	<th width="150" align="center">이 름</th>
	<th width="150" align="center">나 이</th>
	<th width="150" align="center">전화번호</th>
	<th width="150" align="center">주소</th>
	<th width="150" align="center">권한</th>
	</tr>
	</thead>
	<tbody>
	
	<tr>
	<td width="150" align="center">${member.memberId }</td>
	<td width="150" align="center">${member.memberName }</td>
	<td width="150" align="center">${member.memberAge }</td>
	<td width="150" align="center">${member.memberTell }</td>
	<td width="150" align="center">${member.memberAddress }</td>
	<td width="150" align="center">${member.memberAuthor }</td>
	
	</tr>
	
	</tbody>
	
	</table>
	</div>
	<br>
	
	
	<button type="button" onclick="memberEdit('E')">회원정보수정</button>&nbsp;&nbsp;
	<button type="button" onclick="memberEdit('D')">회원정보삭제</button><br>
	<form id="frm" method="post">
		<input type="hidden" name="memberId" value="${member.memberId }">
	</form>
	</div>
	<br>
	<script type="text/javascript">
		function memberEdit(str) {
			if(str == 'E'){ //회원정보 수정
				frm.action="memberEdit.do";
				
			}else{  //회원정보 삭제 confirm - 확인,취소 경고창
				let yn = confirm("회원정보를 삭제합니다.")
				if(yn){ //true면memberDelete.do으로 인동
					frm.action="memberDelete.do";
					
				}else{
					//취소를 누르면 false를 반환해서 동작이 안되도록함. 
					return false;
				}
			}
			//폼을 전송
			frm.submit();
		}
	</script>
</body>
</html>