package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

public class EnterPage extends BasePage {
    private void enter(HttpServletRequest request, Map<String, Object> view) {
        Optional<User> user = userService.findAll().stream().filter(x -> x.getEmail().equals(request.getParameter("email"))).findFirst();
        if (user.isPresent()) {
            if (user.get().getPasswordSha1().equals(userService.getPasswordHash(request.getParameter("password")))) {
                request.getSession().setAttribute("user", user.get());
                throw new RedirectException("/index", "enterDone");
            } else {
                view.put("email", user.get().getEmail());
                view.put("error", "Invalid email or password");
            }
        } else {
            view.put("email", request.getParameter("email"));
            view.put("error", "Invalid email or password");
        }
    }

    public void action() {
        // No operations.
    }
}
