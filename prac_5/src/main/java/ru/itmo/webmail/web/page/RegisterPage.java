package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.EmailConfirmation;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.service.UserService;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class RegisterPage extends Page {
    private void register(HttpServletRequest request, Map<String, Object> view) {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setEmail(request.getParameter("email"));
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");
        try {
            getUserService().validateRegistration(user, password, passwordConfirmation);
        } catch (ValidationException e) {
            view.put("login", user.getLogin());
            view.put("email", user.getEmail());
            view.put("password", password);
            view.put("passwordConfirmation", passwordConfirmation);
            view.put("error", e.getMessage());
            return;
        }
        getUserService().register(user, password);

        EmailConfirmation emailConfirmation = new EmailConfirmation();
        emailConfirmation.setUserId(user.getId());
        emailConfirmation.setSecret(getEmailConfirmationService().generateSecret());
        getEmailConfirmationService().save(emailConfirmation);

        throw new RedirectException("/index", "registrationDone");
    }

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }
}
