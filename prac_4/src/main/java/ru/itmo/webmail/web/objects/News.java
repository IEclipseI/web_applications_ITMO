package ru.itmo.webmail.web.objects;

import java.io.Serializable;

public class News implements Serializable {
    private long userId;

    public long getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    private String text;

    public News(long userId, String text) {
        this.userId = userId;
        this.text = text;
    }
}
