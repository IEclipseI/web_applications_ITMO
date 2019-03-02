package ru.itmo.webmail.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import ru.itmo.webmail.web.exception.RedirectException;

public class LogoutPage extends BasePage {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        request.getSession().removeAttribute("user");
        throw new RedirectException("/index", "logoutDone");
    }
}
