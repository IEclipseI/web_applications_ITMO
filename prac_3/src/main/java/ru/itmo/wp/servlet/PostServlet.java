package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import com.sun.org.apache.xpath.internal.operations.String;
//import com.sun.org.apache.xpath.internal.operations.String;

public class PostServlet extends HttpServlet {
    private static List<Pair> messages = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        HttpSession session = req.getSession();
        Gson gson = new Gson();
        if (uri.contains("message/auth")) {
            String name = req.getParameter("user");
            if (name != null) {
                session.setAttribute("us,er", name);
                resp.getWriter().print(gson.toJson(name));
            } else if (session.getAttribute("user") != null) {
                resp.getWriter().print(gson.toJson(session.getAttribute("user")));
            } else {
                resp.getWriter().print(gson.toJson(""));
            }
        } else if (uri.contains("message/findAll")) {
            resp.getWriter().print(gson.toJson(messages));
        } else if (uri.contains("message/add")) {
            if (!req.getParameter("text").isEmpty()) {
                messages.add(new Pair((String) session.getAttribute("user"), req.getParameter("text")));
            }
        }
        resp.setContentType("application/json");
        resp.getWriter().flush();
    }
    class Pair {
        String user;
        String text;

        Pair(String user, String text) {
            this.user = user;
            this.text = text;
        }
    }
}