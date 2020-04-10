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
	<!-- DB에서 데이터 불러오기 -->
	<div class="container">
		<div class="row">
			<h1>NOTICE LIST	</h1>
			<table class="table table-hover">
				<tr>
					<td>NUM</td>
					<td>TITLE</td>
					<td>NAME</td>
					<td>DATE</td>
					<td>HIT</td>
				</tr>
				<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.num}</td>
					<td><a href="./noticeSelect?num=${dto.num}">${dto.title}</a></td>
					<td>${dto.name}</td>
					<td>${dto.ddate}</td>
					<td>${dto.hit}</td>
					
				</tr>
				</c:forEach>
			</table>
			
			<a href="./noticeAdd" class="btn btn-primary">NOTICE ADD</a>
		</div>
		
	
	
	</div>
	
</body>
</html>