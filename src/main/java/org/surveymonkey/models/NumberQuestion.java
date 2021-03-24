package org.surveymonkey.models;

import javax.persistence.Entity;

/**
 * This class models a Question that accepts number-based answers.
 */
@Entity
public class NumberQuestion extends Question {

    /**
     * The lower bound of the number range.
     */
    int lowerBound;

    /**
     * The upper bound of the number range.
     */
    int upperBound;

    /**
     * Default constructor for a NumberQuestion.
     * Does not set the actual "question" string.
     */
    public NumberQuestion() {
        super();
        type = QuestionType.NUMBER;
    }

    /**
     * Constructor for a NumberQuestion that sets the "question" string.
     *
     * @param question The question to be set.
     */
    public NumberQuestion(String question) {
        super(question);
        type = QuestionType.NUMBER;
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

    public int getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

}