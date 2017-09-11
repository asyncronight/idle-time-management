$(function(){
  var $listSize = $('#list-size');
  length = $listSize.text();
   for (i = 0; i < length; i++) {
    var $ppc = $('.pie-chart-'+i),
    percent = $ppc.data('percent'),
    deg = 360*percent/100;
    if (percent > 40 && percent < 75) {
      $ppc.addClass('gt-50');
      $('gt-50').css('background','orange');
    }
    if (percent > 75 ) {
      $ppc.addClass('gt-50');
      $('gt-50').css('background','green');
    }
    if (percent < 40 ) {
      $('.ppc-progress-fill-'+i).css('background','red');
            $('.ppc-percents span').css('color','#FE9A2E');

    }
    $('.ppc-percents span').css('color','#FBEFEF');
    $('.ppc-percents').css('background','#0B2F3A');
    $('.ppc-percents').css('opacity','0.8');
    $('.ppc-progress-fill-'+i).css('transform','rotate('+ deg +'deg)');
    $('.ppc-percents-span-'+i).html(percent+'%');
  }
});