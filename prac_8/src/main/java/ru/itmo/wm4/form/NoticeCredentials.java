package ru.itmo.wm4.form;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NoticeCredentials {
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 5000)
    @Lob
    private String text;

    @JsonIgnore
    @Size(min = 0, max = 255)
    @Pattern(regexp = "[a-z\\s0-9]*", message = "expected lowercase Latin letters")
    private String stringWithTags;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStringWithTags() {
        return stringWithTags;
    }

    public void setStringWithTags(String stringWithTags) {
        this.stringWithTags = stringWithTags;
    }
}
