<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<body>
  <h1>User Edit</h1>
  
  <form:form modelAttribute="user" action="../edit">
  	<label for="username">Username</label>
	<form:input id="username" path="username"/>
	<form:errors path="username" cssclass="error"></form:errors>
	<br/>
	
	<label for="password">Password</label>
	<form:password path="password" />
	<form:errors path="password" cssclass="error"></form:errors>
	<br/>
	
	<label for="enable">enable</label>	
	<form:checkbox path="enable" />
	<form:errors path="enable" cssclass="error"></form:errors>
	<br/>
	
	<label for="roles">Roles</label>	
	<form:select path="roles" items="${roles}" />
	<br/>
	
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <form:hidden  path="id" />
    
    <a href="../list">Cancel</a>
    <input type="submit" value="Save"/>
  </form:form>
  
</body>
</html>