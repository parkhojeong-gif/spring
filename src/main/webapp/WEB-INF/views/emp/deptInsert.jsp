<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>empInsert.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<h3 id="top">부서등록</h3>
<c:set var="url" value="./deptInsert"/>
<c:if test="${not empty deptVO.department_id}">
<c:set var="url" value="./deptUpdate"/>
</c:if>
<form action="${url }" method="post" name="frm">
	DEPARTMENT_ID <input type="number" name="department_id"
				 <c:if test="${not empty deptVO.department_id}"> readonly="readonly"</c:if>value ="${deptVO.department_id }" ><br>
	DEPARTMENT_NAME  <input name="department_name" value="${deptVO.department_name }"><br>
	MANAGER_ID    	  <input name="manager_id" value="${deptVO.manager_id }"><br>
	LOCATION_ID       <input  name="location_id" value="${deptVO.location_id }"><br>
				
	<button type="submit">등록</button>
	<button type="reset">초기화</button>
	<
</form>

</body>
</html>
