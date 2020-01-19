package com.imperva.smsSending;

import com.imperva.smsSending.models.Message;
//import com.imperva.smsSending.sendingToReceiver.SendingMessage;


import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.*;


public class SendingHandler {
    final Random random = new Random();

    //SendingMessage sendingMessage;
    Map<Future<Boolean>, Message> futureToSendedMessage;



    private void handleFutureResults(List<Future<Boolean>> futures,
                                     List<Message> messages,
                                     Pair<Float, ConcurrentLinkedQueue<Message>> nextQueue) {
        try {
            if (nextQueue != null) {
                float nextInterval = nextQueue.getKey();
                long nextTimeStap = new Date().getTime() + (long) (nextInterval * 1000);
                for (int i = 0; i < futures.size(); i++) {
                    if (!futures.get(i).get()) {
                        messages.get(i).setTimeStapm(nextTimeStap);
                        nextQueue.getValue().add(messages.get(i));
                        //System.out.println("nextInterval" + nextInterval);
                    }
                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public SendingHandler() {
       // sendingMessage = new SendingMessage();
        futureToSendedMessage = new HashMap<>();
    }

    public void sendMessages(List<Message> messages, Pair<Float, ConcurrentLinkedQueue<Message>> nextQueue) {
        try {
            List<Callable<Boolean>> callableTasks = new ArrayList<>();
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for (Message message : messages) {
                callableTasks.add(() -> {
                   // return sendingMessage.sendMessage(message);
                    return sendMessage(message);
                    //System.out.println(Thread.currentThread().getId());
                });
            }

            List<Future<Boolean>> futures = executorService.invokeAll(callableTasks);
            handleFutureResults( futures, messages, nextQueue);

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

    private boolean sendMessage(Message messages) {
       // System.out.println("****  false");
       // return false;

        // simulates 2/3% success
        boolean success =  random.nextInt(3) > 0;
        System.out.println("Message id:" + messages.getId() +  "   " + success + " success");
        return success;
    }
}

