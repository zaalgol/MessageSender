package com.imperva.smsSending.data;

import lombok.Data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
public class SmsMessage extends Message {
    private String senderNumber;
    private String receiverNumber;
    private String content;

    public SmsMessage() {

    }

    public SmsMessage(int id) {
        setId(id);
    }

    public SmsMessage(int id, String senderNumber, String receiverNumber) {
        setId(id);
        this.senderNumber = senderNumber;
        this.receiverNumber = receiverNumber;
    }
}