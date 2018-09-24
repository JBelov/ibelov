
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Create User</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/" method="post">
    <input type="hidden" name="action" value="create"/>
    Name: <input type="text" name="name" title="Name"/><br/>
    Login: <input type="text" name="login" title="Login"/><br/>
    Email: <input type="text" name="email" title="Email"/><br/>
    <input type="submit"/>
</form>
</body>
</html>
