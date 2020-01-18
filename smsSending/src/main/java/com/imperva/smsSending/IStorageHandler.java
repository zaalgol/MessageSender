package com.imperva.smsSending;

import com.imperva.smsSending.data.Message;

public interface IStorageHandler {
    void handleNewMessage(Message message);

    void wakeup();
}
