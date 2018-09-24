package ru.job4j.crud.servlets;

import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class Controller extends HttpServlet {
    /**
     * Logic layout.
     */
    private final ValidateService validateService = ValidateService.getInstance();
    /**
     * Actions dispatching map.
     */
    private final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> dispatch = new HashMap<>();

    /**
     * Initialize actions dispatching map with actions and methods.
     */
    @Override
    public void init() {
        this.load("create", this.createAction());
        this.load("update", this.updateAction());
        this.load("delete", this.deleteAction());
        this.load("createview", this.createView());
        this.load("updateview", this.updateView());

    }

    /**
     * Actions dispatching map loader.
     */
    private void load(String action, BiConsumer<HttpServletRequest, HttpServletResponse> handle) {
        this.dispatch.put(action, handle);
    }

    /**
     * Add new User item.
     */
    private BiConsumer<HttpServletRequest, HttpServletResponse> createAction() {
        return (req, resp) -> {
            validateService.add(
                    new User(
                            req.getParameter("name"),
                            req.getParameter("login"),
                            req.getParameter("email")
                    )
            );
            try {
                resp.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    /**
     * Update item.
     */
    private BiConsumer<HttpServletRequest, HttpServletResponse> updateAction() {
        return (req, resp) -> {
            validateService.update(
                    new User(
                            req.getParameter("id"),
                            req.getParameter("name"),
                            req.getParameter("login"),
                            req.getParameter("email")
                    )
            );
            try {
                resp.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    /**
     * Delete item.
     */
    private BiConsumer<HttpServletRequest, HttpServletResponse> deleteAction() {
        return (req, resp) -> {
            validateService.delete(req.getParameter("id"));
            try {
                resp.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    /**
     * User creation View.
     */
    private BiConsumer<HttpServletRequest, HttpServletResponse> createView() {
        return (req, resp) -> {
            try {
                req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    /**
     * User update View.
     */
    private BiConsumer<HttpServletRequest, HttpServletResponse> updateView() {
        return (req, resp) -> {
            req.setAttribute("user", ValidateService.getInstance().findById(req.getParameter("id")).get());
            try {
                req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    /**
     * GET method. Return all items or item by id.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("users", ValidateService.getInstance().findAll());
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
    }

    /**
     * POST method. Allow to create, update, delete items.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        resp.setContentType("text/html");
        this.dispatch.get(action).accept(req, resp);
    }
}