package com.imperva.smsSending;

import com.imperva.smsSending.data.Message;
import com.imperva.smsSending.data.SmsMessage;
import com.imperva.smsSending.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource(value = "classpath:application.properties")
@EnableScheduling
class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @BeforeEach
    void setUp() {

//        Thread aThread = new Thread(() -> {
//            messageService.newMessage(new Message());
//            for (int i = 0; i < 100; i++) {
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        aThread.getThreadGroup().setDaemon(true);
//        aThread.start();
    }

    @Test
    void newMessage() {
        messageService.newMessage(new SmsMessage());
        for (long i = 0; i < 1000000000; i++) {
            for (long j = 0; j < 1000000000; j++) {

            }
        }
        int y = 0;
//        try {
//            TimeUnit.MINUTES.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    @Test
    void wakeup() {
        // messageService.wakeup();
    }
}