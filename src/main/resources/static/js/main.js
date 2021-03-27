(() => {
    $('#doSurveyTable')
        .transition('swing down in')
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