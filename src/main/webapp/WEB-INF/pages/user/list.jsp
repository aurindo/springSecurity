<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<html>
<body>
	<h1>Users List</h1>

	<table border="2" width="70%" cellpadding="2">
		<tr>
			<th>User Name</th>
			<th>Enable</th>
			<th>Roles</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach var="userItem" items="${listUsers}">
			<tr>
				<td>${userItem.username}</td>
				<td>${userItem.enable}</td>
				<td>${userItem.roles}</td>
				<td><a href="edit/${userItem.id}">Edit</a></td>
				<td><a href="delete/${userItem.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>

   <br/>  
   <a href="add">Add New User</a>
   <br/>
   <a href="<c:url value="/index" />">Home</a>
   

</body>
</html>