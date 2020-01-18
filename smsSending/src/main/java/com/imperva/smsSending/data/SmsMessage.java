package com.imperva.smsSending.data;

import lombok.Data;

@Data
public class SmsMessage extends Message {
    private String senderNumber;
    private String receiverNumber;

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
