import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TextQuestionTest {
    private TextQuestion question;
    public TextQuestionTest(){

    }

    @Before
    public void buildUp(){
        question = new TextQuestion();
    }

    @After
    public void tearDown(){
        question = null;
    }

    @Test
    public void testReturnQuestion(){
        String s = "Test Question 1";
        question.setQuestion(s);
        Assert.assertEquals(s, question.getQuestion());

    }

    @Test
    public void testReturnAnswer(){
        String s = "Test Answer 1";
        question.setAnswer(s);
        Assert.assertEquals(s, question.getAnswer(0));
    }
}
