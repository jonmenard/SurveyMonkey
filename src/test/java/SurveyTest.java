import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SurveyTest {
    private Survey survey;
    private ChoiceQuestion question1;
    private NumberQuestion question2;
    private NumberQuestion question3;
    public SurveyTest(){
    }

    @Before
    public void setUp(){
        survey = new Survey();
        question1 = new ChoiceQuestion();
        question2 = new NumberQuestion();
        question3 = new NumberQuestion();
        question1.addQuestion("Test Question1");
        question2.addQuestion("Test Question2");
        question3.addQuestion("Test Question3");
        survey.addQuestion(question1);
    }

    @After
    public void tearDown(){
        survey = null;
        question1 = null;
        question2 = null;
        question3 = null;
    }

    @Test
    public void testAddQuestion(){
        survey.addQuestion(question2);
        Assert.assertEquals(question2.returnQuestion(), survey.getQuestion(1));
    }

    @Test
    public void testRemoveQuestion(){
        survey.removeQuestion(1);
        survey.addQuestion(question3);
        Assert.assertEquals(question3.returnQuestion(), survey.getQuestion(1));
    }

    @Test
    public void testGetQuestion(){
        Assert.assertEquals(question1.returnQuestion(), survey.getQuestion(0));
    }
}
