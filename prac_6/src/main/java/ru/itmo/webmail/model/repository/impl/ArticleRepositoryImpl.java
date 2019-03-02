package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.Article;
import ru.itmo.webmail.model.exception.RepositoryException;
import ru.itmo.webmail.model.repository.ArticleRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleRepositoryImpl implements ArticleRepository {
    private static final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    @Override
    public Article find(long articleId) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM article WHERE id=?")) {
                statement.setLong(1, articleId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return toArticle(resultSet.getMetaData(), resultSet);
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Article by id.", e);
        }
    }

    @Override
    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM article ORDER BY creationTime DESC")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        articles.add(toArticle(resultSet.getMetaData(), resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Article by id.", e);
        }
        return articles;
    }

    @Override
    public void save(Article article) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO article (userId, hidden, title, text, creationTime) VALUES (?, ?, ?, ?, NOW())",
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, article.getUserId());
                statement.setBoolean(2, article.isHidden());
                statement.setString(3, article.getTitle());
                statement.setString(4, article.getText());
                if (statement.executeUpdate() == 1) {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        article.setId(generatedKeys.getLong(1));
                        article.setCreationTime(findCreationTime(article.getId()));
                    } else {
                        throw new RepositoryException("Can't find id of saved Article.");
                    }
                } else {
                    throw new RepositoryException("Can't save Article.");
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't save Article.");
        }
    }

    @Override
    public void switchHidden(long articleId, boolean hidden) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE article SET hidden=? WHERE id=?",
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setBoolean(1, hidden);
                statement.setLong(2, articleId);
                if (statement.executeUpdate() == 1) {
                    //no actions
                } else {
                    throw new RepositoryException("Can't update Article.");
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't update Article.", e);
        }
    }

    @Override
    public List<Article> findByUserId(long userId) {
        List<Article> articles = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM article WHERE userId=? ORDER BY creationTime DESC")) {
                statement.setLong(1,userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        articles.add(toArticle(resultSet.getMetaData(), resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Article by id.", e);
        }
        return articles;
    }

    private Date findCreationTime(long articleId) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT creationTime FROM article WHERE id=?")) {
                statement.setLong(1, articleId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getTimestamp(1);
                    }
                }
                throw new RepositoryException("Can't find Article.creationTime by id.");
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Article.creationTime by id.", e);
        }
    }

    private Article toArticle(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        Article article = new Article();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i);
            if (columnName.equalsIgnoreCase("id")) {
                article.setId(resultSet.getLong(i));
            } else if (columnName.equalsIgnoreCase("userId")) {
                article.setUserId(resultSet.getLong(i));
            } else if (columnName.equalsIgnoreCase("title")) {
                article.setTitle(resultSet.getString(i));
            } else if (columnName.equalsIgnoreCase("text")) {
                article.setText(resultSet.getString(i));
            } else if (columnName.equalsIgnoreCase("creationTime")) {
                article.setCreationTime(resultSet.getTimestamp(i));
            } else if (columnName.equalsIgnoreCase("hidden")) {
                article.setHidden(resultSet.getBoolean(i));
            } else {
                throw new RepositoryException("Unexpected column 'Article." + columnName + "'.");
            }
        }
        return article;
    }
}
