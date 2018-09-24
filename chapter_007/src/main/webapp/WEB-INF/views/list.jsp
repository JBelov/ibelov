<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Users list</title>
</head>
<body>
<table>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Login</td>
        <td>Email</td>
        <td></td>
        <td></td>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}
            </td>
            <td>${user.name}
            </td>
            <td>${user.login}
            </td>
            <td>${user.email}
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/" method="post">
                    <input type="hidden" name="action" value="updateview"/>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="submit" value="Edit"/>
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/" method="post">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="${pageContext.servletContext.contextPath}/" method="post">
    <input type="hidden" name="action" value="createview"/>
    <input type="submit" value="Create new"/>
</form>
</body>
</html>
