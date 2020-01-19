package com.imperva.smsSending.service;

import com.imperva.smsSending.models.Message;

public interface IMessageService {
    void newMessage(Message message);

    void wakeup();
}
