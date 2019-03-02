package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.service.NewsService;
import ru.itmo.webmail.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class BasePage {
    UserService userService = new UserService();
    NewsService newsService = new NewsService();

    public void before(HttpServletRequest request, Map<String, Object> view) {
    doBeforeAfter(request, view);
    }

    public void after(HttpServletRequest request, Map<String, Object> view) {
        doBeforeAfter(request, view);
    }

    private void doBeforeAfter(HttpServletRequest request, Map<String, Object> view) {
        view.put("userCount", userService.findCount());
        view.put("session", request.getSession());
        view.put("username", ((User) request.getSession().getAttribute("user")) == null
                ? ""
                : ((User) request.getSession().getAttribute("user")).getLogin());
        view.put("newss", newsService.findAll().toArray());
        view.put("userservice", userService);
    }
}