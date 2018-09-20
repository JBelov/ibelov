<%@ page import="ru.job4j.crud.logic.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: belov
  Date: 20.09.2018
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/crud/update" method="post">
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>"/>
    <input type="hidden" name="action" value="update"/>
    Name: <input type="text" name="name" title="Name"
                 value="<%=ValidateService.getInstance().findById(Integer.parseInt(request.getParameter("id"))).get().getName()%>"/><br/>
    Login: <input type="text" name="login" title="Login"
                  value="<%=ValidateService.getInstance().findById(Integer.parseInt(request.getParameter("id"))).get().getLogin()%>"/><br/>
    Email: <input type="text" name="email" title="Email"
                  value="<%=ValidateService.getInstance().findById(Integer.parseInt(request.getParameter("id"))).get().getEmail()%>"/><br/>
    <input type="submit"/>
</form>
</body>
</html>
