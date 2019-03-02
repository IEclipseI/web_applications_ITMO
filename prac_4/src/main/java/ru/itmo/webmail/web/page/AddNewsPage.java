package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.web.objects.News;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class AddNewsPage extends BasePage {
    public void action() {
        // No operations.
    }
    public void addNews(HttpServletRequest request, Map<String, Object> view) {
        if (request.getParameter("textarea") != null
                && !request.getParameter("textarea").isEmpty()
                && request.getSession().getAttribute("user") != null) {
            newsService.saveNews(new News(((User)request.getSession().getAttribute("user")).getId(), request.getParameter("textarea")));
        }
    }
}
