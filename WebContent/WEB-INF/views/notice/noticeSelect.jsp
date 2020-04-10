<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>
	<div class="container">
		<div class="row">
			<h1>Point Select Page</h1>
			<table class="table table-hover">
			<tr class="danger">
				<td>NUM</td>
				<td>TITLE</td>
				<td>NAME</td>
				<td>DATE</td>
				<td>HIT</td>
				
			</tr>
			<tr class="active">
				<td>${dto.num}</td>
				<td>${dto.title}</td>
				<td>${dto.name}</td>
				<td>${dto.ddate}</td>
				<td>${dto.hit}</td>
			</tr>			
			</table>
			<div>${dto.content}</div>
			</div>
			</div>
</body>
</html>