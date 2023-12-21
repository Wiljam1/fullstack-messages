package se.kth.nrww.fullstackmessages.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.kth.nrww.fullstackmessages.model.Message;
import se.kth.nrww.fullstackmessages.services.MessageService;

import java.util.List;

@RestController
@CrossOrigin
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/message")
    Message newMessage(@RequestBody Message message) {
        return messageService.create(message);
    }

    @GetMapping("/messages/{senderId}/{receiverId}")
    List<Message> getConversation(
            @PathVariable Long senderId,
            @PathVariable Long receiverId) {

        return messageService.getConversation(senderId, receiverId);
    }

    @GetMapping("/messages/{senderId}")
    List<Message> getSentMessages(
            @PathVariable Long senderId) {

        return messageService.getSentMessages(senderId);
    }
}
