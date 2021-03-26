(() => {
    $('#doSurveyTable')
        .transition('scale in')
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