package com.imperva.smsSending.messageToReceiver;

import com.imperva.smsSending.data.Message;

public interface IMessageToReceiver {
    boolean sendMessage(Message messages);
}
