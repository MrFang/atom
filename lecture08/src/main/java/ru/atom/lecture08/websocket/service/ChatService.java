package ru.atom.lecture08.websocket.service;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atom.lecture08.websocket.dao.MessageDao;
import ru.atom.lecture08.websocket.dao.UserDao;
import ru.atom.lecture08.websocket.event.Action;
import ru.atom.lecture08.websocket.event.ActionBroker;
import ru.atom.lecture08.websocket.model.Message;
import ru.atom.lecture08.websocket.model.User;

import java.util.List;

@Service
public class ChatService {
    private static final Logger log = LoggerFactory.getLogger(ChatService.class);

    private final UserDao userDao;
    private final MessageDao messageDao;
    private final ActionBroker broker;


    @Autowired
    public ChatService(UserDao userDao, MessageDao messageDao, ActionBroker broker) {
        this.userDao = userDao;
        this.messageDao = messageDao;
        this.broker = broker;
    }

    public void login(@NotNull String name) {
        User newUser = new User()
                .setName(name);

        userDao.save(newUser);
    }

    public void say(@NotNull String name, @NotNull String text) {
        User author = userDao.getByLogin(name);
        Message newMessage = new Message()
                .setAuthor(author)
                .setText(text);

        messageDao.save(newMessage);
        broker.addEvent(Action.MESSAGE_SAVED);
    }

    public boolean userLoggedIn(@NotNull String name) {
        return userDao.getByLogin(name) != null;
    }

    public List<Message> getAllMessages() {
        return messageDao.getAll();
    }
}
