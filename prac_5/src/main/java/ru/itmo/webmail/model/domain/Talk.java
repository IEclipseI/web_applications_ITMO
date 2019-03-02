package ru.itmo.webmail.model.domain;

import java.util.Date;

public class Talk {
    private long id;
    private long sourceUserId;
    private long targetUserId;
    private String text;
    private Date creationTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(long sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    public long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(long targetUserId) {
        this.targetUserId = targetUserId;
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
    public static class TalkView {
        public TalkView(String loginFrom, String loginTo, String text, String creationTime) {
            this.loginFrom = loginFrom;
            this.loginTo = loginTo;
            this.text = text;
            this.creationTime = creationTime;
        }

        String loginFrom;
        String loginTo;
        String text;
        String creationTime;

        public String getLoginFrom() {
            return loginFrom;
        }

        public void setLoginFrom(String loginFrom) {
            this.loginFrom = loginFrom;
        }

        public String getLoginTo() {
            return loginTo;
        }

        public void setLoginTo(String loginTo) {
            this.loginTo = loginTo;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }
    }
}
