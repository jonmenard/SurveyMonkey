public interface Question {

    public void setAnswer(String answer); // Add an answer to the list of answers of this question
    public String getAnswer(int index); // Return an answer from the list of answers to this question
    public void setQuestion(String question); // Add a question to this question
    public String getQuestion();
}
