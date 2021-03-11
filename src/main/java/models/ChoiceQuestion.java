package models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class ChoiceQuestion implements Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String question;

    @Transient
    ArrayList<String> answers;

    @Transient
    ArrayList<String> choices; // Selectable answers for this question

    public ChoiceQuestion() {
        answers = new ArrayList<>();
    }

    public ChoiceQuestion(String question) {
        setQuestion(question);
        answers = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return question;
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
