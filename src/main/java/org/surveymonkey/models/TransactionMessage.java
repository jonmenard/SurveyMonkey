package org.surveymonkey.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class TransactionMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String topic;
    private String message;
    private Timestamp date;


    public TransactionMessage() {

    }


    public TransactionMessage(String topic, String message, Timestamp date) {
        this.topic = topic;
        this.message = message;
        this.date = date;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
