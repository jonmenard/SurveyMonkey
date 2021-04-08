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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "end_user_id", referencedColumnName = "endUserId")
    private EndUser endUser;

    private String surveyName;
    private String surveyDescription;


    /**
     * Default constructor for a Survey.
     */
    public Survey() {
        questions = new ArrayList<>();
        isClosed = false;
        endUser = null;
    }

    /**
     * Constructor for a Survey created by an EndUser.
     */
    public Survey(EndUser endUserID, String name, String description){
        questions = new ArrayList<>();
        isClosed = false;
        this.endUser = endUserID;
        this.surveyName = name;
        this.surveyDescription = description;
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
    public EndUser getEndUser() {
        return endUser;
    }

    /**
     * Set the EndUser ID of the Survey.
     *
     * @param id The EndUser ID to set.
     */
    public void setEndUserId(EndUser id) {
        endUser = id;
    }


    /**
     * Get the EndUser ID of the Survey.
     *
     * @return The surveyName is the name of the survey
     */
    public String getSurveyName() {
        return surveyName;
    }


    /**
     * Set the name of the Survey.
     *
     * @param surveyName is the string containing the survey's name.
     */
    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }


    /**
     * Get the description of the survey
     *
     * @return The surveyDescription is the description of the survey
     */
    public String getSurveyDescription() {
        return surveyDescription;
    }

    /**
     * Set the description of the Survey.
     *
     * @param surveyDescription is the string for containing the description.
     */
    public void setSurveyDescription(String surveyDescription) {
        this.surveyDescription = surveyDescription;
    }

    public long getEndUserId(){
        return this.endUser.getEndUserId();
    }
}