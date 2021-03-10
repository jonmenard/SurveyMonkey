public interface Question {

    public void addAnswer(String answer); // Add an answer to the list of answers of this question
    public String returnAnswer(int index); // Return an answer from the list of answers to this question
    public void addQuestion(String question); // Add a question to this question
    public String returnQuestion();
}
