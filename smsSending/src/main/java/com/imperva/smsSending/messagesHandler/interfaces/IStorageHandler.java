package com.imperva.smsSending.messagesHandler.interfaces;

import com.imperva.smsSending.data.Message;

public interface IStorageHandler {
    void handleNewMessage(Message message);

    void wakeup();
}
