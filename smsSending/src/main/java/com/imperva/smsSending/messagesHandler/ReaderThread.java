package com.imperva.smsSending.messagesHandler;

import com.imperva.smsSending.data.Message;
import com.imperva.smsSending.service.ThreadReaderQueues;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ReaderThread extends Thread {

    ThreadReaderQueues queues;


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
//                    System.out.println("concurrentLinkedQueue" + queues.getQueues().get(i).toString()
//                            + "  size:" + queues.getQueues().get(i).getValue().size());
                    List<Message> messagesToSend = new ArrayList<>();
                    while (!concurrentLinkedQueue.isEmpty()) {
                        Message message = (Message) concurrentLinkedQueue.peek();
                        if (new Date().getTime() < message.getTimeStapm()) {
                            break;
                        }
                        concurrentLinkedQueue.poll();
                        messagesToSend.add(message);
                    }
                    if (!messagesToSend.isEmpty()) {
                        if (i < queues.getQueues().size() - 1) {
                            SendMessages(messagesToSend, queues.getQueues().get(i + 1));
                        } else {
                            SendMessages(messagesToSend, null);
                        }
                    }
                }
            }
        }
    }

    private void SendMessages(List<Message> messagesToSend, Pair<Float, ConcurrentLinkedQueue<Message>> nextQueue) {
        SendingHandler SendingHandler = new SendingHandler();
        SendingHandler.sendMessages(messagesToSend, nextQueue);
    }
}
