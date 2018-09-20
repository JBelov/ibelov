<%@ page import="ru.job4j.crud.models.User" %>
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
    <title>Users list</title>
</head>
<body>
<table>
    <tr>
        <td>Name</td>
        <td>Login</td>
        <td>Email</td>
        <td></td>
        <td></td>
    </tr>
    <% for (User user : ValidateService.getInstance().findAll()) {%>
    <tr>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getLogin()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/update.jsp" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="Edit"/>
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/crud/update" method="post">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="Delete"/>
            </form>
        </td>
    </tr>

    <%} %>
</table>

<form action="<%=request.getContextPath()%>/create.jsp" method="get">
    <input type="submit" value="Create new"/>
</form>
</body>
</html>
