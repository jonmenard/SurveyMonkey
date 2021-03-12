package org.surveymonkey.models;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * This class models a Question that accepts text-based answers.
 */
@Entity
public class TextQuestion extends Question {

    /**
     * List of text-based answers for this question.
     */
    @Transient
    ArrayList<String> answers;

    /**
     * Default constructor for a TextQuestion.
     * Does not set the actual "question" string
     */
    public TextQuestion() {
        answers = new ArrayList<>();
    }

    /**
     * Constructor for a TextQuestion that sets the "question" string
     *
     * @param question The question to be set
     */
    public TextQuestion(String question) {
        setQuestion(question);
        answers = new ArrayList<>();
    }

    /**
     * Add a question to this Question
     *
     * @param question The question to be added
     */
    @Override
    public void setQuestion(String question) {
        if (question != null) {
            this.question = question;
        }
    }

    /**
     * Return the question
     *
     * @return The question
     */
    @Override
    public String getQuestion() {
        String s = "org.surveymonkey.models.Question not yet set!";
        if (question != null) {
            return question;
        }
        return s;
    }

    /**
     * Add an answer with the passed String.
     *
     * @param answer The answer to add to this questions list of answers
     */
    @Override
    public void setAnswer(String answer) {
        if (answer != null) {
            answers.add(answer);
        }
    }

    /**
     * Returns the answer at the given index.
     *
     * @param index The index of the answer to return
     * @return String answer at index position
     */
    @Override
    public String getAnswer(int index) {
        String s = "Index out of range.";
        if (index > -1 && index < answers.size()) {
            return answers.get(index);
        }
        return s;
    }

}
