package se.kth.wiljam.patientjournalmessages.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.kth.wiljam.patientjournalmessages.model.Message;
import se.kth.wiljam.patientjournalmessages.services.MessageService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
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
