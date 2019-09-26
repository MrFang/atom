package ru.atom.lecture08.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.atom.lecture08.websocket.event.Action;
import ru.atom.lecture08.websocket.event.ActionBroker;
import ru.atom.lecture08.websocket.event.Listener;
import ru.atom.lecture08.websocket.model.Message;
import ru.atom.lecture08.websocket.service.ChatService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class WebSocketController extends TextWebSocketHandler implements WebSocketHandler, Listener {
    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);
    private static final CopyOnWriteArrayList<WebSocketSession> activeSessions = new CopyOnWriteArrayList<>();

    @Autowired
    private ChatService service;
    @Autowired
    private ActionBroker broker;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        activeSessions.addIfAbsent(session);
        broker.subscribe(Collections.singletonList(Action.MESSAGE_SAVED), this);
        log.info("Socket Connected: {}", session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("Received {}", message.toString());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        broker.unsubscribe(this);
        activeSessions.remove(session);
        log.info("Socket Closed: [{}] {}", closeStatus.getCode(), closeStatus.getReason());
        super.afterConnectionClosed(session, closeStatus);
    }

    private void updateChat() {
        for (WebSocketSession session : activeSessions) {
            List<Message> messages = service.getAllMessages();
            String history = messages.stream()
                    .reduce(
                            "",
                            (acc, message) -> acc + message.toString() + "\n",
                            (x, y) -> x+y
                    );
            try {
                session.sendMessage(new TextMessage(history));
            } catch (IOException ignore) {
                //
            }
        }
    }

    @Override
    public void callback() {
        updateChat();
    }
}
