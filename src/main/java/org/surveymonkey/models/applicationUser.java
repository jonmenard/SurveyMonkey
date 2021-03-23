package org.surveymonkey.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class applicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Survey> surveys;

    private String name;

    public applicationUser() {
        surveys = new ArrayList<>();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long id) {
        this.userId = id;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Survey getSurvey(int index) {
        return surveys.get(index);
    }

    public void addSurvey(Survey survey) {
        surveys.add(survey);
    }

}
