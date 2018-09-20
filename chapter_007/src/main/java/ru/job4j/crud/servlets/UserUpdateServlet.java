package ru.job4j.crud.servlets;

import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action").equals("delete")) {
            validateService.delete(Integer.parseInt(request.getParameter("id")));
        } else {
            validateService.update(
                    request.getParameter("name"),
                    request.getParameter("login"),
                    request.getParameter("email"),
                    Integer.parseInt(request.getParameter("id"))
            );
        }
        response.sendRedirect("/");
    }
}
