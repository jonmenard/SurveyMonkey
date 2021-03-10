import java.util.ArrayList;

public class ChoiceQuestion implements Question{

    String question;
    ArrayList<String> answers;
    ArrayList<String> choices; // Selectable answers for this question

    public ChoiceQuestion(){
        answers = new ArrayList<String>();
    }

    /**
     * Add a question to this Question
     * @param question The question to be added
     */
    @Override
    public void addQuestion(String question){
        if (question != null) {
            this.question = question;
        }
    }

    /**
     * Return the question
     * @return The question
     */
    public String returnQuestion(){
        String s = "Question not yet set!";
        if (question != null) {
            return question;
        }
        return s;
    }
    /**
     * Add an answer with the passed String.
     * @param answer The answer to add to this questions list of answers
     */
    @Override
    public void addAnswer(String answer) {
        if (answer != null) {
            answers.add(answer);
        }
    }

    /**
     *  Returns the answer at the given index.
     * @param index The index of the answer to return
     * @return String answer at index position
     */
    @Override
    public String returnAnswer(int index) {
        String s = "Index out of range.";
        if (index > -1 && index < answers.size()) {
            return answers.get(index);
        }
        return s;
    }
}
