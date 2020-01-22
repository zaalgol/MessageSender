package com.imperva.smsSending.service;

import com.imperva.smsSending.data.Message;
import com.imperva.smsSending.data.SmsMessage;
import com.imperva.smsSending.messagesHandler.interfaces.IStorageHandler;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Data
@Service
public class MessageService implements IMessageService {

    @Autowired
    IStorageHandler storageHandler;
    //test only
    @PostConstruct
    private void init() {
        for (int i = 1; i <= 20000; i++) {
            newMessage(new SmsMessage(i));
        }
    }

    @Override
    public void newMessage(Message message) {
//        Thread aThread = new Thread(() ->
//
//        );
//        aThread.start();
        storageHandler.handleNewMessage(message);
    }

    @Override
    @Scheduled(fixedDelay = 500)
    public void wakeup() {
        storageHandler.wakeup();
    }

}
