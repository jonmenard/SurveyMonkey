package models;

public interface Question {

    String getAnswer(int index); // Return an answer from the list of answers to this question

    void setAnswer(String answer); // Add an answer to the list of answers of this question

    String getQuestion();

    void setQuestion(String question); // Add a question to this question

}
