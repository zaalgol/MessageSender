package com.imperva.smsSending.models;

import lombok.Data;

@Data
public abstract class Message {
    private int id;
    private long timeStapm;

    public Message(int id) {
        this.id = id;
    }

    public Message() {

    }
}
