package se.kth.nrww.fullstackmessages.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.nrww.fullstackmessages.model.Message;
import se.kth.nrww.fullstackmessages.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message create(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }



    public List<Message> getMessagesBySubject(String subject) {
        return messageRepository.findBySubject(subject);
    }



    public List<Message> getConversation(Long senderId, Long receiverId) {
        return messageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(senderId, receiverId);
    }

    public List<Message> getSentMessages(Long senderId) {
        return messageRepository.findBySender(senderId);
    }
}
