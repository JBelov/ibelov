<%--
  Created by IntelliJ IDEA.
  User: belov
  Date: 20.09.2018
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/crud/create" method="post">
    Name: <input type="text" name="name" title="Name"/><br/>
    Login: <input type="text" name="login" title="Login"/><br/>
    Email: <input type="text" name="email" title="Email"/><br/>
    <input type="submit"/>
</form>
</body>
</html>
