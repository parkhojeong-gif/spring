<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".btnSelect").on("click", function(){
		var empid= $('.sp1').html();
		var firstName= $('.sp2').html();
		opener.document.frm.manager_id.value=empid;
		opener.document.frm.first_name2.value=firstName;
		window.close();
	});
});

</script>
</head>
<body>
<h3>사원검색</h3>
<c:forEach items="${list }" var="emp">
	<span class="sp1"> ${emp.employee_id }</span>
	<span class="sp2"> ${emp.first_name }</span>
	<span> ${emp.email }</span> 
	<span> ${emp.salary }</span>
	  <button type="button" class="btnSelect">선택</button><br>

</c:forEach>

</body>
</html>