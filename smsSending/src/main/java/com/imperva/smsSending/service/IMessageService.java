package com.imperva.smsSending.service;

import com.imperva.smsSending.data.Message;

public interface IMessageService {
    void newMessage(Message message);

    void wakeup();
}
