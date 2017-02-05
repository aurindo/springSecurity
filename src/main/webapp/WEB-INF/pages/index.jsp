<%@page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<body>
	<p>
		<a href="?lang=en">English</a> | <a href="?lang=pt">Portuguese</a>
	</p>
	<h1>WELCOME PAGE</h1>

	<a href="<c:url value="/user/list" />">Users page</a>
	<br/>
	<a href="<c:url value="/admin" />">Admin page</a>
	<br/>
	<br/>
	<a href="<c:url value="/logout" />">Logout</a>

</body>
</html>