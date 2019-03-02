package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.repository.NewsRepository;
import ru.itmo.webmail.model.repository.impl.NewsRepositoryImpl;
import ru.itmo.webmail.web.objects.News;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class NewsService {
    private NewsRepository newsRepository = new NewsRepositoryImpl();

    public void saveNews(News news) {
        newsRepository.save(news);
    }
    public List<News> findAll() {
        return newsRepository.findAll();
    }
}
