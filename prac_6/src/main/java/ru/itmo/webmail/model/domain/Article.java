package ru.itmo.webmail.model.domain;

import java.util.Date;

public class Article {
    private long id;
    private long userId;
    private String title;
    private String text;
    private Date creationTime;
    private boolean hidden = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public static class ArticleView {
        private long id;
        private User user;
        private String title;
        private String text;
        private Date creationTime;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Date getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(Date creationTime) {
            this.creationTime = creationTime;
        }
    }
}
