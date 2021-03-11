package models;

import javax.persistence.*;
import java.util.ArrayList;

public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    ArrayList<Question> questions;

    public Survey() {
        questions = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Add a question to the list of questions.
     *
     * @param question The question to be added
     */
    public void addQuestion(Question question) {
        if (question != null) {
            questions.add(question);
        }
    }

    /**
     * Removes the given Question from this Survey.
     *
     * @param question The Question to remove.
     */
    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    /**
     * Remove a question from the list of questions.
     *
     * @param index The index of the question to be removed
     */
    public void removeQuestion(int index) {
        if (index > -1 && index < questions.size()) {
            questions.remove(index);
        }
    }

    /**
     * Return a question from the list of questions
     *
     * @param index The index of the question to be returned
     * @return The String value of the question at the index specified
     */
    public String getQuestion(int index) {
        String s = "Index out of range.";
        if (index > -1 && index < questions.size()) {
            return questions.get(index).getQuestion();
        }
        return s;
    }

}
