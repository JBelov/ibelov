package ru.job4j.crud.servlets;

import ru.job4j.crud.models.User;
import ru.job4j.crud.logic.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsersServlet extends HttpServlet {

    /**
     * Logic layout.
     */
    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.append(
                "<!DOCTYPE html>\n"
                        + "<html lang=\"en\">\n"
                        + "<head>\n"
                        + "    <meta charset=\"UTF-8\">\n"
                        + "    <title>User Update</title>\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<table>\n"
                        + "    <tr>\n"
                        + "       <td>\n"
                        + "           Name\n"
                        + "       </td> \n"
                        + "        <td>\n"
                        + "            Login\n"
                        + "        </td>\n"
                        + "        <td>\n"
                        + "            Email\n"
                        + "        </td>\n"
                        + "        <td></td>\n"
                        + "        <td></td>\n"
                        + "    </tr>\n");

        for (User user : validateService.findAll()) {
            writer.append(
                    " <tr>\n"
                            + "       <td>\n"
                            + user.getName()
                            + "           \n"
                            + "       </td> \n"
                            + "        <td>\n"
                            + "            \n"
                            + user.getLogin()
                            + "        </td>\n"
                            + "        <td>\n"
                            + "            \n"
                            + user.getEmail()
                            + "        </td>\n"
                            + "        <td>"
                            + "    <form action=\"/crud/update\" method=\"get\">\n"
                            + "        <input type=\"hidden\" name=\"id\" value=\""
                            + user.getId()
                            + "\"/>\n"
                            + "        <input type=\"submit\" value=\"Edit\"/>\n"
                            + "    </form>"
                            + "</td>\n"
                            + "        <td>"
                            + "    <form action=\"/crud/update\" method=\"post\">\n"
                            + "        <input type=\"hidden\" name=\"id\" value=\""
                            + user.getId()
                            + "\"/>\n"
                            + "        <input type=\"hidden\" name=\"action\" value=\"delete\"/>\n"
                            + "        <input type=\"submit\" value=\"Delete\"/>\n"
                            + "    </form>"
                            + "</td>\n"
                            + "    </tr>");
        }
        writer.append(
                "    </table>"
                        + "<form action=\"/crud/create\" method=\"get\">\n"
                        + "        <input type=\"submit\" value=\"Create new user\"/>\n"
                        + "    </form>"
        );

        writer.append("</body>\n"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    }
}