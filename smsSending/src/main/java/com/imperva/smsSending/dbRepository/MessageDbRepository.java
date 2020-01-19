package com.imperva.smsSending.dbRepository;

import com.imperva.smsSending.data.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageDbRepository implements IMessageDbRepository {

    public void storeMessagesInDB(List<Message> messagesToSend) {
        // TODO
    }

    public List<Message> getMessagesFromDB() {
        // TODO
        return null;
    }
}
