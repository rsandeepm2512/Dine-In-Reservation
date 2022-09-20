package com.aexp.dinein.controller;

import com.aexp.dinein.service.MessageProcessor;
import com.aexp.dinein.service.TelegramBotMessenger;
import org.modelmapper.internal.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private TelegramBotMessenger messenger;
    @Autowired
    private MessageProcessor processor;

    @PostMapping("/send/{userId}")
    public ResponseEntity<String> sendMessage(@PathVariable Long userId, String message) {
        messenger.sendMessage(userId, message);
        return new ResponseEntity<>("Message sent successfully!", HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<String> getUpdatedMessage() {
        Pair<String, Long> message = messenger.getMessage();
        return new ResponseEntity<>(message.getLeft(), HttpStatus.OK);
    }

    @GetMapping("/respond")
    public String respondToUserOption() {
        Pair<String, Long> message = messenger.getMessage();

        if(!message.getLeft().isEmpty()) {
            String replyMessage = processor.processMessage(message.getRight(), message.getLeft());

            messenger.sendMessage(message.getRight(), replyMessage);
        }
        return "Response sent successfully!";
    }

}
