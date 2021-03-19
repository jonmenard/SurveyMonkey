package org.surveymonkey.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @OneToMany
    private List<Survey> surveyList;
    private String name;

    public User(){ surveyList = new ArrayList<>();
    }

    public void setName(String n){
        name = n;
    }

    public String getName(){
        return name;
    }

    public void addSurvey(Survey s){
        surveyList.add(s);
    }

    public Survey getSurvey(int index){
        return surveyList.get(index);
    }
}
