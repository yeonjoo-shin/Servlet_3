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
  <h2>Notice Add</h2>
  <form action="./noticeAdd" method="post">
  
     
     <div class="form-group">
      <label for="title">title:</label>
      <input type="text" class="form-control" id="title" placeholder="Enter Title" name="title" >
    </div>
    
    <div class="form-group">
      <label for="Name">Name:</label>
      <input type="text" class="form-control" id="name" placeholder="Enter Name" name="name" >
    </div>   

    
    <div class="form-group">
      <label for="Content">Content:</label>
      <input type="text" class="form-control" id="content" placeholder="Enter Content" name="content" >
    </div>
    
     <button type="submit" class="btn btn-default">Submit</button>
	<!--x form태그안에 데이터를 db로 보내기위해서 a태그x(해당페이지로 이동하겠다는 거지 데이터 저장x),input type=button(x submit 이벤트 실행해줘야함)-->
	<!--O button 타입/ input type=sumit(submit 이벤트 내장 ) -->
	</form>
	</div>
</body>
</html>