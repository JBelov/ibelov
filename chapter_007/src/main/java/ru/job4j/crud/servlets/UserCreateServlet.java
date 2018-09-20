package ru.job4j.crud.servlets;

import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.function.Function;

public class UserCreateServlet extends HttpServlet {
    /**
     * Logic layout.
     */
    private final ValidateService validateService = ValidateService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validateService.add(
                new User(
                        request.getParameter("name"),
                        request.getParameter("login"),
                        request.getParameter("email")
                )
        );

        response.sendRedirect("/crud/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.append(
                "<!DOCTYPE html>\n"
                        + "<html lang=\"en\">\n"
                        + "<head>\n"
                        + "    <meta charset=\"UTF-8\">\n"
                        + "    <title>User Creation</title>\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<form action=\"/crud/create\" method=\"post\">\n"
                        + "    Name: <input type=\"text\" name=\"name\"/><br/>\n"
                        + "    Login: <input type=\"text\" name=\"login\"/><br/>\n"
                        + "    Email: <input type=\"text\" name=\"email\"/><br/>\n"
                        + "    <input type=\"submit\"/>\n"
                        + "</form>\n"
                        + "</body>\n"
                        + "</html>");
        writer.flush();
    }
}
