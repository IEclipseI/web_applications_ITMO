package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class AdminPage extends Page {
    @Override
    public void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);

        if (getUser() == null) {
            throw new RedirectException("/index");
        }
    }

    public void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private List<User> find(HttpServletRequest request, Map<String, Object> view) {
        return getUserService().findAll();
    }


    private Map<String, Object> switchAdmin(HttpServletRequest request, Map<String, Object> view) {
        long userToChangeId = Long.parseLong(request.getParameter("userToChangeId"));
        User was = getUserService().find(userToChangeId);
        if (getUser().isAdmin()) {
            getUserService().switchStatus(userToChangeId, !was.isAdmin());
            view.put("success", true);
        } else {
            view.put("success", false);
        }
        return view;
    }
}
