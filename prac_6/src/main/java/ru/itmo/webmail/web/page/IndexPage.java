package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Article;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndexPage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void registrationDone(HttpServletRequest request, Map<String, Object> view) {
        view.put("message", "You have been registered");
    }

    private List<Article.ArticleView> findArticles(HttpServletRequest request, Map<String, Object> view) {
        List<Article> all = getArticleService().findAll();
        List<Article.ArticleView> allViews = new ArrayList<>();
        for (Article a : all) {
            if (!a.isHidden()) {
                Article.ArticleView articleView = new Article.ArticleView();
                articleView.setId(a.getId());
                articleView.setUser(getUserService().find(a.getUserId()));
                articleView.setTitle(a.getTitle());
                articleView.setText(a.getText());
                articleView.setCreationTime(a.getCreationTime());
                allViews.add(articleView);
            }
        }
        return allViews;
    }
}
