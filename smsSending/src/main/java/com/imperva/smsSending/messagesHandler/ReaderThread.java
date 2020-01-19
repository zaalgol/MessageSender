package com.imperva.smsSending.messagesHandler;

import com.imperva.smsSending.data.Message;
import com.imperva.smsSending.messagesHandler.interfaces.ISendingHandler;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ReaderThread extends Thread {

    private ThreadReaderQueues queues;

    public ReaderThread(ThreadReaderQueues queues) {
        this.queues = queues;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < queues.getQueues().size(); i++) {
                    ConcurrentLinkedQueue concurrentLinkedQueue = queues.getQueues().get(i).getValue();
                    List<Message> messagesToSend = new ArrayList<>();
                    while (!concurrentLinkedQueue.isEmpty()) {
                        Message message = (Message) concurrentLinkedQueue.peek();
                        if (new Date().getTime() < message.getTimestapm()) {
                            break;
                        }
                        concurrentLinkedQueue.poll();
                        messagesToSend.add(message);
                    }
                    if (!messagesToSend.isEmpty()) {
                        if (i < queues.getQueues().size() - 1) { // not final queue
                            SendMessages(messagesToSend, queues.getQueues().get(i + 1));
                        } else {
                            SendMessages(messagesToSend, null); // final queue
                        }
                    }
                }
            }
        }
    }

    private void SendMessages(List<Message> messagesToSend, Pair<Float, ConcurrentLinkedQueue<Message>> nextQueue) {
        ISendingHandler SendingHandler = new SendingHandler();
        SendingHandler.sendMessages(messagesToSend, nextQueue);
    }
}
