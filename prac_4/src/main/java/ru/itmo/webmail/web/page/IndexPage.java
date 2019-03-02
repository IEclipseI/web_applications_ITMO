package ru.itmo.webmail.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class IndexPage extends BasePage {
    private void action() {
        // No operations.
    }

    private void registrationDone(Map<String, Object> view) {
        view.put("message", "You have been registered");
    }

    private void enterDone(Map<String, Object> view) {
        view.put("message", "You have been entered");
    }

    private void logoutDone(Map<String, Object> view) {
        view.put("message", "You have been logged out");
    }
}
