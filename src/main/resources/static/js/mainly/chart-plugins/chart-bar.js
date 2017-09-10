$(function() {
  $("#bars li .bar").each( function( key, bar ) {
    var percentage = $(this).data('percentage');


 $(this).animate({
        'height' : percentage + '%'
        }, 1000);

        if(percentage >=75){
          $(this).css('background','#1ade6c');

        }
        else if(percentage >=50){
          $(this).css('background','#1ade00');

        }
        else if(percentage>=25){
          $(this).css('background','#c10000');

        }else{
          $(this).css('background','#ed0000');

        }
  });

});