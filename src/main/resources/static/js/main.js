$(function() {
    $("#chart .bars li .bar").each( function( key, bar ) {
        var percentage = $(this).data('percentage');
        $(this).animate({
            'height' : percentage + '%'
        }, 1000);
    });
});

/*
########### Cercle chart #############
 */
$(function() {
    $('.circle-graph').easyPieChart({
        scaleColor: false,
        lineWidth: 20,
        lineCap: 'butt',
        barColor: '#a378aa',
        trackColor: '#e7b8ef' ,
        size: 150,
        animate: 800
    });
});