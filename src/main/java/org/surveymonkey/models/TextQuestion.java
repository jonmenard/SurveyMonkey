package org.surveymonkey.models;

import javax.persistence.Entity;

/**
 * This class models a Question that accepts text-based answers.
 */
@Entity
public class TextQuestion extends Question {

    /**
     * Default constructor for a TextQuestion.
     * Does not set the actual "question" string.
     */
    public TextQuestion() {
        super();
        type = QuestionType.TEXT;
    }

    /**
     * Constructor for a TextQuestion that sets the "question" string.
     *
     * @param question The question to be set.
     */
    public TextQuestion(String question) {
        super(question);
        type = QuestionType.TEXT;
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

}
