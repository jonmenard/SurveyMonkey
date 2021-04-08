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


    /**
     * The name of this EndUser.
     */
    private String name;

    /**
     * Default constructor for an EndUser.
     */
    public EndUser() {

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



    @Override
    public String toString() {
        return "{\"id\":" + id + ", \"name\":" + name + "}";
    }

}