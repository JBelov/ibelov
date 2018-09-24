<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Update User</title>
</head>
<body>
<c:set var="user" value="${user}"></c:set>
<form action="${pageContext.servletContext.contextPath}/" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    <input type="hidden" name="action" value="update"/>
    Name: <input type="text" name="name" title="Name"
                 value="${user.name}"/><br/>
    Login: <input type="text" name="login" title="Login"
                  value="${user.login}"/><br/>
    Email: <input type="text" name="email" title="Email"
                  value="${user.email}"/><br/>
    <input type="submit"/>
</form>
</body>
</html>