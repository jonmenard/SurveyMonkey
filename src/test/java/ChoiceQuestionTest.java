import models.ChoiceQuestion;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChoiceQuestionTest {

    private ChoiceQuestion question;

    public ChoiceQuestionTest() {
    }

    @Before
    public void buildUp() {
        question = new ChoiceQuestion();
    }

    @After
    public void tearDown() {
        question = null;
    }

    @Test
    public void testReturnQuestion() {
        String s = "Test models.Question 1";
        question.setQuestion(s);
        Assert.assertEquals(s, question.getQuestion());
    }

    @Test
    public void testReturnAnswer() {
        String s = "Test Answer 1";
        question.setAnswer(s);
        Assert.assertEquals(s, question.getAnswer(0));
    }

}
