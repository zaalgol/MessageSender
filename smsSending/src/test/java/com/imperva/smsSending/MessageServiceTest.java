package com.imperva.smsSending;

import com.imperva.smsSending.data.Message;
import com.imperva.smsSending.data.SmsMessage;
import com.imperva.smsSending.service.MessageService;
import com.imperva.smsSending.service.QueueHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
@ContextConfiguration(classes = {MessageService.class, QueueHandler.class})
@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource(value = "classpath:application.properties")
@EnableScheduling
class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @Autowired
    QueueHandler queueHandler;

    @Test
    void newMessage() {
        for (int i = 0; i < 200000; i++){
            messageService.newMessage(new SmsMessage(i));
        }
    }

    @Test
    void wakeup() {
        messageService.wakeup();
    }
}