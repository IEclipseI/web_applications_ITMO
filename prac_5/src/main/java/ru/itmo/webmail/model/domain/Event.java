package ru.itmo.webmail.model.domain;

import java.util.Date;

public class Event {
    private long id;
    private long userId;
    private eventType type;
    private Date creationTime;

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public eventType getType() {
        return type;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setType(eventType type) {
        this.type = type;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public enum eventType{
        ENTER, LOGOUT
    }
}
