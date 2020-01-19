package com.imperva.smsSending.messageToReceiver;

import com.imperva.smsSending.data.Message;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MessageToReceiver implements IMessageToReceiver {
    final Random random = new Random();

    public boolean sendMessage(Message messages) {
        // simulates 2/3% success
        boolean success = random.nextInt(3) > 0;
        System.out.println("Message id:" + messages.getId() + "   " + success + " success");
        return success;
    }
}
