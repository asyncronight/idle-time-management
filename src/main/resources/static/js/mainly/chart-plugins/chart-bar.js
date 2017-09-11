$(function() {
  $("#bars li .bar").each( function( key, bar ) {
    var percentage = $(this).data('percentage');


 $(this).animate({
        'height' : percentage + '%'
        }, 1000);

        if(percentage >60){
          $(this).css('background','green');

        }
        else if(percentage >40){
          $(this).css('background','orange');
        }else{
          $(this).css('background','red');

        }
  });

});