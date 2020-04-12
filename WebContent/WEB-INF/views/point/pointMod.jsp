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
</head>
<body>
	<c:import url="../template/header.jsp"></c:import>

<div class="container">
  <h2>Point Mod</h2>
  <form action="./pointMod" method="post">
  
    <div class="form-group">
      <label for="Name">Name:</label>
      <input type="text" class="form-control" id="name" placeholder="Enter Name" name="name" value="${dto.name}" ><!--name과 파라미터 이름 동일하게!! value값이 넘어와서 화면에 출력-->
    </div>
    
     <div class="form-group">
      <label for="Num">Num:</label>
      <input type="text" class="form-control" id="num" placeholder="Enter Num" name="num" value="${dto.num}" readonly="readonly" >
    </div> 
    
    <div class="form-group">
      <label for="Kor">Kor:</label>
      <input type="text" class="form-control" id="kor" placeholder="Enter Kor" name="kor" value="${dto.kor}"  >
    </div>
    
     <div class="form-group">
      <label for="Eng">Eng:</label>
      <input type="text" class="form-control" id="eng" placeholder="Enter Eng" name="eng" value="${dto.eng}" >
    </div>
    
     <div class="form-group">
      <label for="Math">Math:</label>
      <input type="text" class="form-control" id="math" placeholder="Enter Math" name="math" value="${dto.math}" >
    </div>
    
     <button type="submit" class="btn btn-primary">Mod2</button>
     
    
	<!--x form태그안에 데이터를 db로 보내기위해서 a태그x(해당페이지로 이동하겠다는 거지 데이터 저장x),input type=button(x submit 이벤트 실행해줘야함)-->
	<!--O button 타입/ input type=sumit(submit 이벤트 내장 ) -->
	</form>
	</div>
</body>
</html>