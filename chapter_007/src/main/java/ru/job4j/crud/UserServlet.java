package ru.job4j.crud;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 * <p>
 * Presentation layout. Servlet provides a CRUD service for Users Storage. Using ValidateService as logic layout.
 * Usage:
 * GET:
 * /[no params] - returns all users in storage;
 * /?id=[id] - returns user by id or "No such user."
 * POST:
 * /?action=add&name=[name]&login=[login]&email=[email] - creates new user with name, login, email.
 * /?action=update&id=[id]&name=[name]&login=[login]&email=[email] - updates fields for user by id.
 * /?action=delete&id=[id] - deletes user by id.
 */
public class UserServlet extends HttpServlet {

    /**
     * Logic layout.
     */
    private final ValidateService validateService = ValidateService.getInstance();
    /**
     * Actions dispatching map.
     */
    private final Map<String, Function<HttpServletRequest, Boolean>> dispatch = new HashMap<>();

    /**
     * Initialize actions dispatching map with actions and methods.
     *
     * @param config
     */
    @Override
    public void init(ServletConfig config) {
        this.load("add", this.addItem());
        this.load("update", this.updateItem());
        this.load("delete", this.deleteItem());
    }

    /**
     * Actions dispatching map loader.
     *
     * @param action
     * @param handle
     */
    private void load(String action, Function<HttpServletRequest, Boolean> handle) {
        this.dispatch.put(action, handle);
    }

    /**
     * Add new User item.
     *
     * @return
     */
    private Function<HttpServletRequest, Boolean> addItem() {
        return req -> validateService.add(
                new User(
                        req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email")
                )
        );
    }

    /**
     * Update item.
     *
     * @return
     */
    private Function<HttpServletRequest, Boolean> updateItem() {
        return req -> validateService.update(
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                Integer.parseInt(req.getParameter("id"))
        );
    }

    /**
     * Delete item.
     *
     * @return
     */
    private Function<HttpServletRequest, Boolean> deleteItem() {
        return req -> validateService.delete(Integer.parseInt(req.getParameter("id")));
    }

    /**
     * GET method. Return all items or item by id.
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter result = resp.getWriter();
        if (req.getParameter("id") == null) {
            if (!validateService.findAll().isEmpty()) {
                for (User user : validateService.findAll()) {
                    result.println(user.toString());
                    result.println("<br/>");
                }
            } else {
                result.println("List is empty.");
            }
        } else {
            int id = Integer.parseInt(req.getParameter("id"));
            result.println(validateService.findById(id).isPresent()
                    ? validateService.findById(id).get().toString() : "No such user.");
        }
        result.flush();
    }

    /**
     * POST method. Allow to create, update, delete items.
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(this.dispatch.get(action).apply(req));
    }
}