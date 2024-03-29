package se.kth.nrww.fullstackmessages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.kth.nrww.fullstackmessages.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    //List<Message> findBySenderUsername(String senderUsername);

    //List<Message> findByReceiverUsername(String receiverUsername);

    List<Message> findBySubject(String subject);

    //List<Message> findBySenderUsernameAndReceiverUsernameAndSubject(String senderUsername, String receiverUsername, String subject);

    //List<Message> findBySenderUsernameAndReceiverUsername(String senderUsername, String receiverUsername);

    List<Message> findBySender(Long senderId);

    @Query("SELECT m FROM Message m " +
            "WHERE (m.sender = :senderId AND m.receiver = :receiverId) " +
            "OR (m.receiver = :senderId AND m.sender = :receiverId)")
    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(
            @Param("senderId") Long senderId,
            @Param("receiverId") Long receiverId
    );

}
