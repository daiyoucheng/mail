package com.mail.bean;

import io.swagger.annotations.ApiModel;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by dai_youcheng on 2017/4/18.
 */
@ApiModel
public class mailContent {
        private String replyTo;
    private String[] to;
    private String[] cc;
    private Date sentDate;
    private String subject;
    private String text;

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "mailContent{" +
                "replyTo='" + replyTo + '\'' +
                ", to=" + Arrays.toString(to) +
                ", cc=" + Arrays.toString(cc) +
                ", sentDate=" + sentDate +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
