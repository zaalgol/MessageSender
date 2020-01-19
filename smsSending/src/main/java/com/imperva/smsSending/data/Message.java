package com.imperva.smsSending.data;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public abstract class Message {
    private int id;

    @Id
    private long timestapm;

    public Message(int id) {
        this.id = id;
    }

    public Message() {

    }
}
