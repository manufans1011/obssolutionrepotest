<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body> 
	<c:choose>
	    <c:when test="${loginSuccess=='false'}">
	        Wrong User ID or Password
	        <br />
	    </c:when>    
	    <c:otherwise>
	    	<div style="padding: 10px">
	    		<h3>Welcome ${username}</h3>
				<br/>
				<table border=1>
					<tr style="background-color:blue;">
						<th>Department</th>
						<th>Student ID</th>
						<th>Marks</th>
						<th>Pass %</th>
					</tr>
				
					<c:forEach var="student" items="${student_list}">
						<% boolean isFirstData = true;%>	
						
						<c:forEach var="studentDetail" items="${student.value}">
							<tr>
								<% if(isFirstData){%>	
									<td rowspan="${student.value.size()}">${student.key}</td>	
								<% }%>			
								<td><a href="javaScript:alert('${studentDetail.studentName}')">${studentDetail.studentID}</a></td>
								<td>${studentDetail.mark}</td>					
								<% if(isFirstData){%>	
									<td rowspan="${student.value.size()}"><fmt:formatNumber type="number" maxFractionDigits="2" value="${student.value.stream().filter(p->p.getMark() > 50).toList().size()/student.value.size() * 100}"/></td>	
								<% }%>
								<% isFirstData = false;%>	
							</tr>
						</c:forEach>
						<% isFirstData = true;%>	
					</c:forEach>
				</table>
	    	</div>
			
	    </c:otherwise>
	</c:choose>
</body>
</html>