(() => {
    $(() => {
        $('.addQuestionToSurvey')
            .submit(() => {
                var $q = $('#question');
                $q.val($q.val() + '?');
            })
        ;
        $('.choiceQuestionChoiceOnAddQuestionChoice')
            .transition('drop in')
        ;
        $('#doSurveyTable')
            .transition('vertical flip in')
        ;
        $('.jiggle.buttons .button')
            .transition({
                animation: 'jiggle',
                duration: 800,
                interval: 200
            })
        ;
        $('select')
            .dropdown()
        ;
    });
});