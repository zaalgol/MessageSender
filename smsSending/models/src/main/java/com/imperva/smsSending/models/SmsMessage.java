package com.imperva.smsSending.models;

import com.imperva.smsSending.models.Message;
import lombok.Data;

@Data
public class SmsMessage extends Message {
    private String senderNumber;
    private String receiverNumber;
    private String content;

    public SmsMessage() {

    }

    public SmsMessage(int id) {
        this.setId(id);
    }

    public SmsMessage(int id, String senderNumber, String receiverNumber) {
        this.setId(id);
        this.senderNumber = senderNumber;
        this.receiverNumber = receiverNumber;
    }
}
