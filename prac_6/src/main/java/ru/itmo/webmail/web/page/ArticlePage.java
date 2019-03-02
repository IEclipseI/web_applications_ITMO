package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class ArticlePage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    @Override
    public void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);

        if (getUser() == null) {
            throw new RedirectException("/index");
        }
    }

    private Map<String, Object> createArticle(HttpServletRequest request, Map<String, Object> view) {
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        try {
            getArticleService().validateArticle(title, text);
        } catch (ValidationException e) {
            view.put("title", title);
            view.put("text", text);
            view.put("error", e.getMessage());
            return view;
        }
        Article article = new Article();
        long id = (long) request.getSession().getAttribute(USER_ID_SESSION_KEY);
        article.setUserId((long) request.getSession().getAttribute(USER_ID_SESSION_KEY));
        article.setText(text);
        article.setTitle(title);
        getArticleService().saveArticle(article);
        view.put("success", true);
        return view;
    }
}
