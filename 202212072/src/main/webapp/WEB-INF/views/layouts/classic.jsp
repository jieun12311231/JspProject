<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<!-- tiles.xml 변수를 name으로 넣어주는것 -->
<!-- 위치 정해주기 -->
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"></tiles:getAsString></title>
</head>
<body>
	<tiles:insertAttribute name="menu"/>
	<tiles:insertAttribute name="body"/>
	<tiles:insertAttribute name="footer"/>
</body>
</html>