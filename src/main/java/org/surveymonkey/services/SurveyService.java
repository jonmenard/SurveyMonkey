package org.surveymonkey.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.surveymonkey.models.ChoiceQuestion;
import org.surveymonkey.models.NumberQuestion;
import org.surveymonkey.models.Question;
import org.surveymonkey.models.Survey;
import org.surveymonkey.repositories.SurveyRepository;
import org.surveymonkey.services.iservices.ISurveyService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class SurveyService implements ISurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    @Override
    public Survey findById(long id) {
        return surveyRepository.findById(id);
    }

    public List<Survey> findSurveysByUserId(long userId){
        return surveyRepository.findByEndUserIDOrderById(userId);
    }

    @Override
    public Survey save(Survey survey) {
        return surveyRepository.save(survey);
    }

    public Iterable<Survey> findAll(){return surveyRepository.findAll();}

    @Override
    public void swapQuestion(int surveyId, int questionId, String direction){

        Survey survey = this.findById(surveyId);
        List<Question> questions = survey.getQuestions();

        Question last = questions.get(0);
        if(last.getId() != questionId || !direction.equals("Up")) {
            for (int i = 0; i < questions.size(); i++) {
                Question current = questions.get(i);
                if (current.getId() == questionId) {
                    if (direction.equals("Up")) {
                        questions.set((i - 1), current);
                        questions.set(i, last);
                        break;
                    } else if (direction.equals("Down")) {
                        if ((i + 1) < questions.size()) {
                            questions.set(i, questions.get(i + 1));
                            questions.set(i + 1, current);
                        }
                        break;
                    }
                } else {
                    last = current;
                }
            }
        }

        survey.setQuestions(questions);
        this.save(survey);
    }



    @Override
    public HashMap<Integer, ArrayList<Integer>> getSurveyStatistics(int surveyId){

        Survey survey = this.findById(surveyId);
        List<Question> questionList = survey.getQuestions();
        HashMap<Integer, ArrayList<Integer>>  surveyStatisticsData = new HashMap<Integer, ArrayList<Integer>>();

        for(int i = 0; i < questionList.size(); i++){
            Question question = questionList.get(i);
            if(question instanceof NumberQuestion) {
                NumberQuestion numberQuestion = (NumberQuestion) question;
                List<String> answers = numberQuestion.getAnswers();
                ArrayList<Integer> dataSet = new ArrayList<Integer>();
                for (int j = 0; j < answers.size(); j++) {
                    if(!answers.get(j).equals("")) {
                        dataSet.add(Integer.parseInt(answers.get(j)));
                    }
                }
                surveyStatisticsData.put((int) question.getId(), dataSet);
            }else if(question instanceof ChoiceQuestion){
                ChoiceQuestion choiceQuestion = (ChoiceQuestion) question;
                List<String> answers = choiceQuestion.getAnswers();
                List<String> options = choiceQuestion.getChoices();
                ArrayList<String> choices = new ArrayList<String>();
                ArrayList<Integer> occurences = new ArrayList<Integer>();
                for (int j = 0; j < options.size(); j++) {

                    String option = (options.get(j));
                    int occurrence = Collections.frequency(answers, option);
                    occurences.add(occurrence);

                }
                surveyStatisticsData.put((int) question.getId(), occurences);
            }

        }
        return surveyStatisticsData;
    }


}