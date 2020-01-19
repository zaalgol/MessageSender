package com.imperva.smsSending.messagesHandler;

import com.imperva.smsSending.data.Message;
import com.imperva.smsSending.messageToReceiver.IMessageToReceiver;
import com.imperva.smsSending.messageToReceiver.MessageToReceiver;
import com.imperva.smsSending.messagesHandler.interfaces.ISendingHandler;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class SendingHandler implements ISendingHandler {
    private IMessageToReceiver messageSending;

    public SendingHandler() {
        messageSending = new MessageToReceiver();
    }

    private void handleFutureResults(List<Future<Boolean>> futures,
                                     List<Message> messages,
                                     Pair<Float, ConcurrentLinkedQueue<Message>> nextQueue) {
        try {
            if (nextQueue != null) {
                float nextInterval = nextQueue.getKey();
                long nextTimeStap = new Date().getTime() + (long) (nextInterval * 1000);
                for (int i = 0; i < futures.size(); i++) {
                    if (!futures.get(i).get()) {
                        messages.get(i).setTimestapm(nextTimeStap);
                        nextQueue.getValue().add(messages.get(i));
                    }
                }

            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void sendMessages(List<Message> messages, Pair<Float, ConcurrentLinkedQueue<Message>> nextQueue) {
        try {
            List<Callable<Boolean>> callableTasks = new ArrayList<>();
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for (Message message : messages) {
                callableTasks.add(() -> messageSending.sendMessage(message));
            }

            List<Future<Boolean>> futures = executorService.invokeAll(callableTasks);
            handleFutureResults(futures, messages, nextQueue);

            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

