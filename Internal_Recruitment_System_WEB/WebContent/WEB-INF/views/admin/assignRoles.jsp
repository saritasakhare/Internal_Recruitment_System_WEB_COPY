<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Assign Roles</h1>
	
	<div class="header">
            <div class="icon"></div>
            <div class="title">Internal Recruitment System</div>
            <div class="desc-ln"> Recruit Employees For Projects.</div>
        </div>
        
        <div class="body">
            <h1>Hello From R ecruitMent System User ADMIN </h1>
           <div class="error" align="center" style="color: red; font-family: sans-serif;">
	          <c:if test="${msg!=null}">${msg}</c:if>
	       </div>
                    <div>
                    
        	<f:form  action="processAssignRoles.mvc" method="post" modelAttribute="user">
        		<table>
        		<tr>
        			<th> User Id : </th>
        			<td><f:input path="userId"/></td>
        		</tr>
        		<tr>
        			<th> Role </th>
        			<td>
        				<f:select path="role">
        					<f:option value="ADMIN"/>
        					<f:option value="ROLE"/>
        					<f:option value="RMGE"/>
        				</f:select>
        				<f:errors path="role" />
        			</td>
        		</tr>
        		<tr>
        			<th colspan="2"><input type="submit" value="Update"> </th>
        		</tr>
        		</table>
        	</f:form>
        </div>
        </div>
        
        <div class="footer"> 
        </div>
        
</body>
</html>