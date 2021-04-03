package org.surveymonkey.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class models a Question that accepts certain choices as answers.
 */
@Entity
public class ChoiceQuestion extends Question {

    /**
     * The choices that a user can choose from when answering this question.
     */
    @ElementCollection
    private List<String> choices;

    /**
     * Default constructor for a ChoiceQuestion.
     * Does not set the actual "question" string.
     */
    public ChoiceQuestion() {
        super();
        choices = new ArrayList<>();
        type = QuestionType.CHOICE;
    }

    /**
     * Constructor for a ChoiceQuestion that sets the "question" string.
     *
     * @param question The question to be set.
     */
    public ChoiceQuestion(String question) {
        super(question);
        choices = new ArrayList<>();
        type = QuestionType.CHOICE;
    }

    /**
     * Returns the answer at the given index.
     *
     * @param index The index of the answer to return.
     * @return String answer at index position.
     */
    @Override
    public String getAnswer(int index) {
        String s = "Index out of range";
        if (index > -1 && index < answers.size()) {
            return answers.get(index);
        }
        return s;
    }

    /**
     * Add an answer with the passed String.
     *
     * @param answer The answer to add to this questions list of answers.
     */
    @Override
    public void setAnswer(String answer) {
        if (answer != null) {
            answers.add(answer);
        }
    }

    /**
     * Get the choices of this ChoiceQuestion.
     * @return The choices of this ChoiceQuestion in a String List.
     */
    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    /**
     * Add a choice the question can be answered with.
     *
     * @param choice the choice to be added to the choices list.
     */
    public void addChoice(String choice) {
        this.choices.add(choice);
    }

}