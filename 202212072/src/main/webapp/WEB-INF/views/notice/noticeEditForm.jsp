<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<div align="center">

			<div>
				<h1>공지사항 수정!!!</h1>
			</div>
			<div align="center">
				<form id="frm" action="noticeUpdate.do" method="post"
					enctype="multipart/form-data">
					<div>
						<table border="1">
							<tr>
								<th width="150">작성자</th>
								<td width="200">${notices.noticeWriter }</td>
								<th width="150">작성일자</th>
								<td width="200"><input type="date" name="noticeDate"
									value="${notices.noticeDate }"></td>
							</tr>
							<tr>
								<th>제목</th>
								<td colspan="3"><input type="text" size="76"
									name="noticeTitle" value="${notices.noticeTitle }"></td>
							</tr>
							<tr>
								<th>내용</th>
								<td colspan="3"><textarea rows="10" cols="80"
										name="noticeSubject" >${notices.noticeSubject }</textarea></td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td colspan="3">${notices.noticeFile }<input type="file" name="nfile"
									></td>
							</tr>
						</table>
					</div>
					<br>
					<!-- key값을 가지고 넘어가서 숨겨놓음 중요함!! -->
					<div>
						<input type="hidden" name="noticeId" value="${notices.noticeId }">
						<input type="hidden" name="noticeHit" value="${notices.noticeHit }">						
						<input type="hidden" name="noticeWriter" value="${notices.noticeWriter }">
						<input type="submit" value="등록">&nbsp;&nbsp; 
						<input
							type="reset" value="취소">
						
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>