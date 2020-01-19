package com.imperva.smsSending.messagesHandler;

import com.imperva.smsSending.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableAsync
@EnableScheduling
@Configuration
public class SmsSendingApplication {
    @Autowired
    IMessageService messageService;

    public static void main(String[] args) {
        SpringApplication.run(SmsSendingApplication.class, args);
    }

}
