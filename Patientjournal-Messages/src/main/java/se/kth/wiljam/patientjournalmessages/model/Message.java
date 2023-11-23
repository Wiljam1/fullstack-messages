package se.kth.wiljam.patientjournalmessages.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference(value = "sent-messages")
    private Long sender;

    @JsonBackReference(value = "received-messages")
    private Long receiver;

    private String subject;
    private String content;
    private Date date;

    public Long getId() {return id;}

    public Long getSender() {return sender;}

    public Long getReceiver() {return receiver;}

    public String getContent() {return content;}

    public Date getDate() {return date;}

    public String getSubject() {return subject;}

    public void setId(Long id) {this.id = id;}

    public void setSender(Long sender) {this.sender = sender;}

    public void setReceiver(Long receiver) {this.receiver = receiver;}

    public void setContent(String content) {this.content = content;}

    public void setDate(Date date) {this.date = date;}

    public void setSubject(String subject) {this.subject = subject;}


}
