package com.example.financialmaster;

import android.net.Uri;

public class Message {

    private String message;
    private String sender;
    private String csvFilePath;

    public Message(String message, String sender, Uri csvFilePath) {
        this.message = message;
        this.sender = sender;
        this.csvFilePath = String.valueOf(csvFilePath);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getCsvFilePath() {
        return csvFilePath;
    }

    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }
}
