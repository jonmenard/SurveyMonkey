package org.surveymonkey.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Question is an abstract class that models a Survey question.
 * There are three different types of Questions.
 * Questions must belong to a Survey.
 */
@Entity
public abstract class Question {

    /**
     * The Question's unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    /**
     * A String containing the actual "question" for the Question object.
     */
    protected String question;

    /**
     * A list of answers to the question.
     */
    @ElementCollection
    protected List<String> answers;

    /**
     * The type of Question.
     */
    protected QuestionType type;

    /**
     * Possible Question subtypes.
     */
    protected enum QuestionType {
        CHOICE, NUMBER, TEXT
    }

    /**
     * The default Question constructor; initializes the answers field.
     */
    public Question() {
        answers = new ArrayList<>();
    }

    /**
     * A parameterized Question constructor; initializes the question and answers fields.
     *
     * @param question The question to be answered.
     */
    public Question(String question) {
        this();
        setQuestion(question);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the actual "question" that this Question object represents.
     *
     * @return A string containing the "question".
     */
    public String getQuestion() {
        String s = "Question not yet set!";
        if (question != null) {
            return question;
        }
        return s;
    }

    /**
     * Sets the actual "question" that this Question object represents.
     *
     * @param question A string containing the question.
     */
    public void setQuestion(String question) {
        if (question != null) {
            this.question = question;
        }
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public QuestionType getType() {
        return type;
    }

    /**
     * Gets an answer from the list of answers for the given question.
     *
     * @param index Index for the desired answer.
     * @return a String containing the answer.
     */
    public abstract String getAnswer(int index);

    /**
     * Adds an answer to the list of answers for this question.
     *
     * @param answer the answer to add.
     */
    public abstract void setAnswer(String answer);

}
