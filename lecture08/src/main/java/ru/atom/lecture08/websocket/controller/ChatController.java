package ru.atom.lecture08.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.atom.lecture08.websocket.service.ChatService;

@Controller
@RequestMapping("chat")
public class ChatController {
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    private final ChatService service;

    @Autowired
    public ChatController(ChatService service) {
        this.service = service;
    }

    @RequestMapping(
            path = "login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestParam("name") String name){
        if (name.length() < 1) {
            return ResponseEntity.badRequest().body("Too short name");
        }

        if (service.userLoggedIn(name)) {
            return ResponseEntity.badRequest().body("Already logged in");
        }

        service.login(name);
        service.say(name, "logged in");

        log.info("{} logged in", name);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(
            path = "say",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> say(@RequestParam("name") String name, @RequestParam("msg") String message) {
        if (message.length() < 1) {
            return ResponseEntity.badRequest().body("Too short name");
        }

        if (!service.userLoggedIn(name)) {
            log.info("User not logged in");
            return ResponseEntity.badRequest().body("Not logged in");
        }

        service.say(name, message);

        log.info("{} said {}", name, message);

        return ResponseEntity.ok().build();
    }
}
