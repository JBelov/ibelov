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

    private final ValidateService validateService = ValidateService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validateService.add(
                new User(
                        request.getParameter("name"),
                        request.getParameter("login"),
                        request.getParameter("email")
                )
        );
        response.sendRedirect("/");
    }
}
