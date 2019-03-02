package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.ArticleRepository;
import ru.itmo.webmail.model.repository.impl.ArticleRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    private ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateArticle(String title, String text) throws ValidationException {
        if (title == null || text == null) {
            throw new ValidationException("Incorrect article");
        }
        if (title.length() < 2 || title.length() > 100) {
            throw new ValidationException("Title length should be between 2 and 100");
        }
        if (text.length() < 2) {
            throw new ValidationException("Text is too short(less than 2)");
        }
        // TODO
    }

    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    public Article findArticleById(long id) {
        return articleRepository.find(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void switchHidden(long articleId, boolean newValue) {
        articleRepository.switchHidden(articleId, newValue);
    }

    public List<Article> findArticlesByUserId(long userId) {
        return articleRepository.findByUserId(userId);
    }
}
