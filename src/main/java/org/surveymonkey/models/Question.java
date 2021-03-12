package org.surveymonkey.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Question is an abstract class that models a Survey question.
 * There are three different types of Questions.
 * Questions must belong to a Survey.
 */
@Entity
public abstract class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    /**
     * A String containing the actual "question" for the Question object.
     */
    protected String question;

    /**
     * Get an answer from the list of answers for the given question.
     * @param index Index for the desired answer
     * @return a String containing the answer
     */
    public abstract String getAnswer(int index);

    /**
     * Add an answer to the list of answers for this question
     * @param answer the answer to add
     */
    public abstract void setAnswer(String answer);

    /**
     * Get the actual "question" that this Question object represents
     * @return A string containing the "question"
     */
    public abstract String getQuestion();

    /**
     * Set the actual "question" that this Question object represents
     * @param question A string containing the question
     */
    public abstract void setQuestion(String question);

    /**
     * Get the ID of the Question
     * @return The ID of the Question
     */
    public long getId() {
        return id;
    }

    /**
     * Set the ID of the Question
     * @param id The ID to set
     */
    public void setId(long id) {
        this.id = id;
    }

}
