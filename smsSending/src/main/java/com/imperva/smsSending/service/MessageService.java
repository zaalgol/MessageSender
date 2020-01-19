package com.imperva.smsSending.service;

import com.imperva.smsSending.data.Message;
import com.imperva.smsSending.messagesHandler.interfaces.IStorageHandler;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Data
@Service
public class MessageService implements IMessageService {

    @Autowired
    IStorageHandler storageHandler;
// test only
//    @PostConstruct
//    private void init() {
//        for (int i = 1; i <= 20; i++) {
//            newMessage(new SmsMessage(i));
//        }
//    }

    @Override
    public void newMessage(Message message) {
        Thread aThread = new Thread(() ->
                storageHandler.handleNewMessage(message)
        );
        aThread.start();
    }

    @Override
    @Scheduled(fixedDelay = 500)
    public void wakeup() {
        storageHandler.wakeup();
    }

}
