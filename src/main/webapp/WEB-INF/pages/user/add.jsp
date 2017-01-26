<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<body>
  <p><spring:message code="add.title"/></p>
  <p><spring:message code="add.text"/></p>  
  
  <p>
    <a href="?lang=en">English</a> |
    <a href="?lang=pt">Portuguese</a>
  </p>  
  
  <form:form method="POST" modelAttribute="user">
	<form:input path="firstName" />
	<form:errors path="firstName" cssclass="error"></form:errors>
	
	<form:input path="age" />
	<form:errors path="age" cssclass="error"></form:errors>
    <input type="submit" value="Add"/>
  </form:form>
  
</body>
</html>