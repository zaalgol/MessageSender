package com.imperva.smsSending.messagesHandler;

import com.imperva.smsSending.data.Message;

public interface IStorageHandler {
    void handleNewMessage(Message message);

    void wakeup();
}
