package org.surveymonkey.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    protected String question;

    public abstract String getAnswer(int index); // Return an answer from the list of answers to this question

    public abstract void setAnswer(String answer); // Add an answer to the list of answers of this question

    public abstract String getQuestion();

    public abstract void setQuestion(String question); // Add a question to this question

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
