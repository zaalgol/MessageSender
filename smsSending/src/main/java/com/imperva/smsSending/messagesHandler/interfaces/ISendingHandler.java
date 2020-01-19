package com.imperva.smsSending.messagesHandler.interfaces;

import com.imperva.smsSending.data.Message;
import javafx.util.Pair;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface ISendingHandler {
    void sendMessages(List<Message> messages, Pair<Float, ConcurrentLinkedQueue<Message>> nextQueue);
}
