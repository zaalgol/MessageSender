package com.imperva.smsSending.service;


import com.imperva.smsSending.messagesHandler.IStorageHandler;
import com.imperva.smsSending.messagesHandler.ReaderThread;
import com.imperva.smsSending.data.Message;
import javafx.util.Pair;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Slf4j
@Data
@Service
public class QueueHandler implements IStorageHandler {

    private static List<ThreadReaderQueues> queues;
    @Value("${num.of.immediate.queues}")
    private int numOfImmediateQueues;

    @Value("${intervals}")
    private String intervalsStr;

    private List<Float> intervals;
    private List<Thread> readerThreads;
    private Random random;

    @PostConstruct
    private void init() {
        queues = new ArrayList<>();
        random = new Random();
        readerThreads = new ArrayList<>();
        setIntervals();
        setQueues();
        initReaderThreads();
    }

    private void initReaderThreads() {
        queues.forEach(queue -> {
            ReaderThread readerThread = new ReaderThread(queue);
            //Thread thread = new Thread(readerThread);
            readerThreads.add(readerThread);
            readerThread.start();
        });
    }


    private void setQueues() {
        for (int i = 0; i < numOfImmediateQueues; i++) {
            ThreadReaderQueues threadQueue = new ThreadReaderQueues();
            threadQueue.getQueues().add(new Pair<>(0.0F, new ConcurrentLinkedQueue<>()));
            for (float interval : intervals) {
                threadQueue.getQueues().add(new Pair<>(interval, new ConcurrentLinkedQueue<>()));
            }
            queues.add(threadQueue);
        }
    }

    private void setIntervals() {
        intervals = Arrays.stream(intervalsStr.split(","))
                .map(Float::parseFloat)
                .collect(Collectors.toList());
    }

    private void immediateQueueAllocatior(Message message) {
        ThreadReaderQueues randomThreadReaderQueues = queues.get(random.nextInt(numOfImmediateQueues));
        randomThreadReaderQueues.getQueues().get(0).getValue().add(message);
    }

    public void handleNewMessage(Message message) {
        immediateQueueAllocatior(message);
    }

    public void wakeup() {
        for (Thread readerThread : readerThreads) {
            synchronized (readerThread) {
                readerThread.notify();
            }
        }
    }
}

