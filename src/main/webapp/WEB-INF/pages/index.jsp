<%@page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<body>
	<h1>WELCOME PAGE</h1>

	<a href="<c:url value="/logout" />">Logout</a>

	<p>
		<spring:message code="add.title" />
	</p>
	<p>
		<spring:message code="add.text" />
	</p>

	<p>
		<a href="?lang=en">English</a> | <a href="?lang=pt">Portuguese</a>
	</p>

</body>
</html>