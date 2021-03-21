package org.surveymonkey.unit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.surveymonkey.models.ChoiceQuestion;
import org.surveymonkey.models.NumberQuestion;
import org.surveymonkey.models.Survey;

public class SurveyTest {

    private Survey survey;
    private ChoiceQuestion question1;
    private NumberQuestion question2;
    private NumberQuestion question3;

    public SurveyTest() {
    }

    @Before
    public void setUp() {
        survey = new Survey();
        question1 = new ChoiceQuestion();
        question2 = new NumberQuestion();
        question3 = new NumberQuestion();
        question1.setQuestion("Test Question1");
        question2.setQuestion("Test Question2");
        question3.setQuestion("Test Question3");
        survey.addQuestion(question1);
    }

    @After
    public void tearDown() {
        survey = null;
        question1 = null;
        question2 = null;
        question3 = null;
    }

    @Test
    public void testAddQuestion() {
        survey.addQuestion(question2);
        Assert.assertEquals(question2.getQuestion(), survey.getQuestion(1));
    }

    @Test
    public void testRemoveQuestion() {
        survey.removeQuestion(1);
        survey.addQuestion(question3);
        Assert.assertEquals(question3.getQuestion(), survey.getQuestion(1));
    }

    @Test
    public void testGetQuestion() {
        Assert.assertEquals(question1.getQuestion(), survey.getQuestion(0));
    }

    @Test
    public void testIsClosed() {
        Assert.assertEquals(false, survey.isClosed());
    }

    @Test
    public void testMarkAsClosed() {
        survey.markAsClosed();
        Assert.assertEquals(true, survey.isClosed());
    }
}
