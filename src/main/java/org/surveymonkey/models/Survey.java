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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;

    private boolean isClosed;

    /**
     * Default constructor for a Survey.
     */
    public Survey() {
        questions = new ArrayList<>();
        isClosed = false;
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

    public List<Question> getQuestions() {
        return questions;
    }

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

}
