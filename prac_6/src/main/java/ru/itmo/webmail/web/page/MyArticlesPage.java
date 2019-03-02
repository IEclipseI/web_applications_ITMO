package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class MyArticlesPage extends Page {
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

    private List<Article> findArticles(HttpServletRequest request, Map<String, Object> view) {
        return getArticleService().findArticlesByUserId(getUser().getId());
    }

    private Map<String, Object> switchHidden(HttpServletRequest request, Map<String, Object> view) {
        Long userId = (Long) request.getSession().getAttribute(USER_ID_SESSION_KEY);
        long articleId = Long.parseLong(request.getParameter("articleId"));
        Article was = getArticleService().findArticleById(articleId);
        if (was.getUserId() == userId) {
            getArticleService().switchHidden(articleId, !was.isHidden());
            view.put("success", true);
        } else {
            view.put("success", false);
        }
        return view;
    }
}
