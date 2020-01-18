package com.imperva.smsSending.service;

import com.imperva.smsSending.data.Message;
import javafx.util.Pair;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Data
public class ThreadReaderQueues {
    private List<Pair<Float, ConcurrentLinkedQueue<Message>>> queues;
    ThreadReaderQueues() {
        queues = new ArrayList<>();
    }
}
