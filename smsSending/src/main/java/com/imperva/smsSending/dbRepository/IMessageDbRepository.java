package com.imperva.smsSending.dbRepository;

import com.imperva.smsSending.data.Message;

import java.util.List;

public interface IMessageDbRepository {
    void storeMessagesInDB(List<Message> messagesToSend);

    List<Message> getMessagesFromDB();
}
