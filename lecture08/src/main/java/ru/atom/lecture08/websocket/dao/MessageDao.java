package ru.atom.lecture08.websocket.dao;

import org.springframework.stereotype.Repository;
import ru.atom.lecture08.websocket.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Repository
public class MessageDao {
    private static final BlockingQueue<Message> messages = new LinkedBlockingQueue<>();

    public void save(Message message) {
        messages.offer(message);

    }

    public List<Message> getAll() {
        return new ArrayList<>(messages);
    }
}
