package org.surveymonkey.unit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.surveymonkey.models.ChoiceQuestion;
import org.surveymonkey.models.NumberQuestion;
import org.surveymonkey.models.Survey;

public class TestSurvey {

    private Survey survey;
    private ChoiceQuestion choiceQuestion;
    private NumberQuestion numberQuestion1;
    private NumberQuestion numberQuestion2;

    public TestSurvey() {
    }

    @Before
    public void setUp() {
        survey = new Survey();
        choiceQuestion = new ChoiceQuestion();
        numberQuestion1 = new NumberQuestion();
        numberQuestion2 = new NumberQuestion();
        choiceQuestion.setQuestion("Test choiceQuestion");
        numberQuestion1.setQuestion("Test numberQuestion1");
        numberQuestion2.setQuestion("Test numberQuestion2");
        survey.addQuestion(choiceQuestion);
    }

    @After
    public void tearDown() {
        survey = null;
        choiceQuestion = null;
        numberQuestion1 = null;
        numberQuestion2 = null;
    }

    @Test
    public void testAddQuestion() {
        survey.addQuestion(numberQuestion1);
        Assert.assertEquals(numberQuestion1.getQuestion(), survey.getQuestion(1));
    }

    @Test
    public void testRemoveQuestion() {
        survey.removeQuestion(1);
        survey.addQuestion(numberQuestion2);
        Assert.assertEquals(numberQuestion2.getQuestion(), survey.getQuestion(1));
    }

    @Test
    public void testGetQuestion() {
        Assert.assertEquals(choiceQuestion.getQuestion(), survey.getQuestion(0));
    }

    @Test
    public void testIsClosed() {
        Assert.assertFalse(survey.isClosed());
    }

    @Test
    public void testMarkAsClosed() {
        survey.markAsClosed();
        Assert.assertTrue(survey.isClosed());
    }

}