package com.imperva.smsSending.data;

import lombok.Data;

@Data
public abstract class Message {
    private int id;
    private String content;
    private long timeStapm;

    public Message(int id) {
        this.id = id;
    }

    public Message() {

    }
}
