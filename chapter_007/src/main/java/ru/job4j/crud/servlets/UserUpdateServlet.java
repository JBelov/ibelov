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
    /**
     * Logic layout.
     */
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
        response.sendRedirect("/crud/list");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));

        if (validateService.findById(id).isPresent()) {
            writer.append(
                    "<!DOCTYPE html>\n"
                            + "<html lang=\"en\">\n"
                            + "<head>\n"
                            + "    <meta charset=\"UTF-8\">\n"
                            + "    <title>User Update</title>\n"
                            + "</head>\n"
                            + "<body>\n"
                            + "<form action=\"/crud/update\" method=\"post\">\n"
                            + "    ID: "
                            + id
                            + "<br/><input type=\"hidden\" name=\"id\" value=\""
                            + id
                            + "\">"
                            + "<input type=\"hidden\" name=\"action\" value=\"update\">"
                            + "    Name: <input type=\"text\" name=\"name\"value=\""
                            +
                            validateService
                                    .findById(id)
                                    .get()
                                    .getName()
                            + "\"><br/>"
                            + "    Login: <input type=\"text\" name=\"login\"value=\""
                            +
                            validateService
                                    .findById(id)
                                    .get()
                                    .getLogin()
                            + "\"><br/>"
                            + "    Email: <input type=\"text\" name=\"email\"value=\""
                            +
                            validateService
                                    .findById(id)
                                    .get()
                                    .getEmail()
                            + "\"><br/>"
                            + "    <input type=\"submit\"/>\n"
                            + "</form>\n"
                            + "</body>\n"
                            + "</html>");
        } else {
            writer.append("No such user");
        }
        writer.flush();
    }
}
