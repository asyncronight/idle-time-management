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
