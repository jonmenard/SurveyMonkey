//package org.surveymonkey.kafka;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//
//@Entity
//public class KafkaAnalytics {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long kafkaAnalysisId;
//
//    /**
//     * The collection of Surveys that this EndUser has.
//     */
//
//
//    /**
//     * The name of this EndUser.
//     */
//    private long surveyCreated;
//
//    private long surveyAnswered;
//
//    private long databaseUpdate;
//
//    private long databaseSelect;
//
//    private long addQuestionChoiceTemplateVisited;
//    private long changeQuestionBoundsTemplateVisited;
//    private long creatQuestionTemplateVisited;
//    private long createSurveyTemplateVisited;
//    private long createUserTemplateVisited;
//    private long displayAllOpenSurveysTemplateVisited;
//    private long displayUserSurveysTemplateVisited;
//    private long doSurveyTemplateVisited;
//    private long errorTemplateVisited;
//    private long fillSurveyTemplateVisited;
//    private long indexTemplateVisited;
//    private long responseThankYouTemplateVisited;
//    private long surveyCreatedTemplateVisited;
//    private long viewResponsesTemplateVisited;
//    private long TemplateVisited;
//
//
//    public KafkaAnalytics(){
//
//    }
//
//
//    public long getKafkaAnalysisId() {
//        return kafkaAnalysisId;
//    }
//
//    public void setKafkaAnalysisId(long kafkaAnalysisId) {
//        this.kafkaAnalysisId = kafkaAnalysisId;
//    }
//
//    public long getSurveyCreated() {
//        return surveyCreated;
//    }
//
//    public void setSurveyCreated(long surveyCreated) {
//        this.surveyCreated = surveyCreated;
//    }
//
//    public long getSurveyAnswered() {
//        return surveyAnswered;
//    }
//
//    public void setSurveyAnswered(long surveyAnswered) {
//        this.surveyAnswered = surveyAnswered;
//    }
//
//    public long getDatabaseUpdate() {
//        return databaseUpdate;
//    }
//
//    public void setDatabaseUpdate(long databaseUpdate) {
//        this.databaseUpdate = databaseUpdate;
//    }
//
//    public long getDatabaseSelect() {
//        return databaseSelect;
//    }
//
//    public void setDatabaseSelect(long databaseSelect) {
//        this.databaseSelect = databaseSelect;
//    }
//
//    public long getAddQuestionChoiceTemplateVisited() {
//        return addQuestionChoiceTemplateVisited;
//    }
//
//    public void setAddQuestionChoiceTemplateVisited(long addQuestionChoiceTemplateVisited) {
//        this.addQuestionChoiceTemplateVisited = addQuestionChoiceTemplateVisited;
//    }
//
//    public long getChangeQuestionBoundsTemplateVisited() {
//        return changeQuestionBoundsTemplateVisited;
//    }
//
//    public void setChangeQuestionBoundsTemplateVisited(long changeQuestionBoundsTemplateVisited) {
//        this.changeQuestionBoundsTemplateVisited = changeQuestionBoundsTemplateVisited;
//    }
//
//    public long getCreatQuestionTemplateVisited() {
//        return creatQuestionTemplateVisited;
//    }
//
//    public void setCreatQuestionTemplateVisited(long creatQuestionTemplateVisited) {
//        this.creatQuestionTemplateVisited = creatQuestionTemplateVisited;
//    }
//
//    public long getCreateSurveyTemplateVisited() {
//        return createSurveyTemplateVisited;
//    }
//
//    public void setCreateSurveyTemplateVisited(long createSurveyTemplateVisited) {
//        this.createSurveyTemplateVisited = createSurveyTemplateVisited;
//    }
//
//    public long getCreateUserTemplateVisited() {
//        return createUserTemplateVisited;
//    }
//
//    public void setCreateUserTemplateVisited(long createUserTemplateVisited) {
//        this.createUserTemplateVisited = createUserTemplateVisited;
//    }
//
//    public long getDisplayAllOpenSurveysTemplateVisited() {
//        return displayAllOpenSurveysTemplateVisited;
//    }
//
//    public void setDisplayAllOpenSurveysTemplateVisited(long displayAllOpenSurveysTemplateVisited) {
//        this.displayAllOpenSurveysTemplateVisited = displayAllOpenSurveysTemplateVisited;
//    }
//
//    public long getDisplayUserSurveysTemplateVisited() {
//        return displayUserSurveysTemplateVisited;
//    }
//
//    public void setDisplayUserSurveysTemplateVisited(long displayUserSurveysTemplateVisited) {
//        this.displayUserSurveysTemplateVisited = displayUserSurveysTemplateVisited;
//    }
//
//    public long getDoSurveyTemplateVisited() {
//        return doSurveyTemplateVisited;
//    }
//
//    public void setDoSurveyTemplateVisited(long doSurveyTemplateVisited) {
//        this.doSurveyTemplateVisited = doSurveyTemplateVisited;
//    }
//
//    public long getErrorTemplateVisited() {
//        return errorTemplateVisited;
//    }
//
//    public void setErrorTemplateVisited(long errorTemplateVisited) {
//        this.errorTemplateVisited = errorTemplateVisited;
//    }
//
//    public long getFillSurveyTemplateVisited() {
//        return fillSurveyTemplateVisited;
//    }
//
//    public void setFillSurveyTemplateVisited(long fillSurveyTemplateVisited) {
//        this.fillSurveyTemplateVisited = fillSurveyTemplateVisited;
//    }
//
//    public long getIndexTemplateVisited() {
//        return indexTemplateVisited;
//    }
//
//    public void setIndexTemplateVisited(long indexTemplateVisited) {
//        this.indexTemplateVisited = indexTemplateVisited;
//    }
//
//    public long getResponseThankYouTemplateVisited() {
//        return responseThankYouTemplateVisited;
//    }
//
//    public void setResponseThankYouTemplateVisited(long responseThankYouTemplateVisited) {
//        this.responseThankYouTemplateVisited = responseThankYouTemplateVisited;
//    }
//
//    public long getSurveyCreatedTemplateVisited() {
//        return surveyCreatedTemplateVisited;
//    }
//
//    public void setSurveyCreatedTemplateVisited(long surveyCreatedTemplateVisited) {
//        this.surveyCreatedTemplateVisited = surveyCreatedTemplateVisited;
//    }
//
//    public long getViewResponsesTemplateVisited() {
//        return viewResponsesTemplateVisited;
//    }
//
//    public void setViewResponsesTemplateVisited(long viewResponsesTemplateVisited) {
//        this.viewResponsesTemplateVisited = viewResponsesTemplateVisited;
//    }
//
//    public long getTemplateVisited() {
//        return TemplateVisited;
//    }
//
//    public void setTemplateVisited(long templateVisited) {
//        TemplateVisited = templateVisited;
//    }
//
//
//
//
//
//
//
//    /**
//     * Default constructor for an EndUser.
//     */
//
//
//
//
//}
