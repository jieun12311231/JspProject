<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/book.js"></script>

</head>
<body>
	<div align="center">
		<!-- 도서 등록 폼 -->
		<h3>도서등록</h3>
		<form name="bookFrm" method="post">
			<table>
				<tr>
					<th><label for="code">도서코드</label></th>
					<td><input type="text" name="bookCode" id="bookCode"></td>
				</tr>
				<tr>
					<th><label for="title">도서명</label></th>
					<td><input type="text" name="bookTitle" id="bookTitle"></td>
				</tr>
				<tr>
					<th><label for="author">저자</label></th>
					<td><input type="text" name="bookAuthor" id="bookAuthor"></td>
				</tr>
				<tr>
					<th><label for="press">출판사</label></th>
					<td><input type="text" name="bookPress" id="bookPress"></td>
				</tr>
				<tr>
					<th><label for="price">금액</label></th>
					<td><input type="text" name="bookPrice" id="bookPrice"></td>
				</tr>
				<tr>
					<td><input type="submit" value="저장"></td>
					<td><input type="button" value="선택삭제" id="delbnt"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="show"></div>
</body>
</html>