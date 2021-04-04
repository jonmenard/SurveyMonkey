package org.surveymonkey.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class models a EndUser that accepts Surveys which are stored in a List.
 */
@Entity
public class EndUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * The collection of Surveys that this EndUser has.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Survey> surveys;

    /**
     * The name of this EndUser.
     */
    private String name;

    /**
     * Default constructor for an EndUser.
     */
    public EndUser() {
        surveys = new ArrayList<>();
    }

    /**
     * Returns the ID of this EndUser.
     * @return The ID for this EndUser.
     */
    public long getId() {
        return id;
    }

    /**
     * Set the ID of this EndUser.
     * @param id The ID to replace this EndUsers ID.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the List of Surveys this EndUser holds.
     * @return The List of Surveys for this EndUser.
     */
    public List<Survey> getSurveys() {
        return surveys;
    }

    /**
     * Set the List of Surveys this EndUser has.
     * @param surveys The List of Surveys to replace the current List of this EndUser.
     */
    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    /**
     * Return the name of this EndUser.
     * @return The name of this EndUser.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of this EndUser.
     * @param name The name to be set for this EndUser.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the Survey at a certain index for the list of Surveys this EndUser has.
     * @param index The index of the Survey to be returned.
     * @return The Survey at the specified index.
     */
    public Survey getSurvey(int index) {
        return surveys.get(index);
    }

    /**
     * Add a Survey to the list of Surveys this EndUser has.
     * @param survey The Survey to be added to this EndUser.
     */
    public void addSurvey(Survey survey) {
        surveys.add(survey);
    }

    @Override
    public String toString() {
        return "{\"id\":" + id + ",\"surveys\":" + getSurveys() + ",\"name\":" + name + "}";
    }

}