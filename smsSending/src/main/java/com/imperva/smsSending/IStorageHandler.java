package com.imperva.smsSending;

import com.imperva.smsSending.models.Message;

public interface IStorageHandler {
    void handleNewMessage(Message message);

    void wakeup();
}
