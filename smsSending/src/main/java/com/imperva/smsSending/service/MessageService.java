package com.imperva.smsSending.service;

import com.imperva.smsSending.messagesHandler.IStorageHandler;
import com.imperva.smsSending.data.Message;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Service
public class MessageService implements IMessageService {

    @Autowired
    IStorageHandler storageHandler;

    @Override
    public void newMessage(Message message) {
        Thread aThread = new Thread(() ->
            storageHandler.handleNewMessage(message)
        );
        aThread.start();
    }

    @Override
    @Scheduled(initialDelay = 5000, fixedDelay = 500)
    public void wakeup() {
        storageHandler.wakeup();
    }

}
