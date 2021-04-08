package org.surveymonkey.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class models a Survey.
 * Surveys can have many Questions.
 * Surveys are open by default, but can be marked as closed.
 * When a Survey is closed, it no longer accepts answers to any questions.
 */
@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * The Questions constituting this Survey.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;

    /**
     * Indicates whether this Survey is open or closed.
     */
    private boolean isClosed;

    /**
     * EndUser ID of the EndUser that creates this survey.
     */
    @Column(name="end_user_id")
    private long endUserID;

    /**
     * Default constructor for a Survey.
     */
    public Survey() {
        questions = new ArrayList<>();
        isClosed = false;
        endUserID = 0;
    }

    /**
     * Constructor for a Survey created by an EndUser.
     */
    public Survey(long endUserID){
        questions = new ArrayList<>();
        isClosed = false;
        this.endUserID = endUserID;
    }

    /**
     * Get the ID of the Survey.
     *
     * @return The ID of the Survey.
     */
    public long getId() {
        return id;
    }

    /**
     * Set the ID of the Survey.
     *
     * @param id The ID to set.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the List of Questions for this Survey.
     * @return The List of Questions for this Survey.
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Set the List of Questions for this Survey.
     * @param questions The List of Questions to replace this Surveys current List.
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * Check whether or not the survey is accepting responses.
     *
     * @return True if the Survey is closed, false otherwise.
     */
    public boolean isClosed() {
        return this.isClosed;
    }

    /**
     * Mark the Survey as closed (no longer accepting responses).
     */
    public void markAsClosed() {
        this.isClosed = true;
    }

    /**
     * Add a question to the list of questions.
     *
     * @param question The question to be added.
     */
    public void addQuestion(Question question) {
        if (question != null) {
            questions.add(question);
        }
    }

    /**
     * Return a question from the list of questions.
     *
     * @param index The index of the question to be returned.
     * @return The String value of the question at the index specified.
     */
    public String getQuestion(int index) {
        String s = "Index out of range";
        if (index > -1 && index < questions.size()) {
            return questions.get(index).getQuestion();
        }
        return s;
    }

    /**
     * Removes the given Question from this Survey.
     *
     * @param question The Question to remove.
     */
    public void removeQuestion(Question question) {
        questions.remove(question);

    }

    /**
     * Remove a question from the list of questions.
     *
     * @param index The index of the question to be removed.
     */
    public void removeQuestion(int index) {
        if (index > -1 && index < questions.size()) {
            questions.remove(index);
        }
    }

    /**
     * Gets the Question having the given ID.
     *
     * @param id The ID of the Question to get.
     */
    public Question findQuestion(int id) {
        for (Question question : questions) {
            if (id == question.getId()) {
                return question;
            }
        }
        return null;
    }

    /**
     * Get the EndUser ID of the Survey.
     *
     * @return The EndUser ID of the Survey.
     */
    public long getEndUserId() {
        return endUserID;
    }

    /**
     * Set the EndUser ID of the Survey.
     *
     * @param id The EndUser ID to set.
     */
    public void setEndUserId(long id) { endUserID = id;
    }

}