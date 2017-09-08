$(function(){
  var $listSize = $('#list-size');
  length = $listSize.text();
<<<<<<< HEAD
   for (i = 0; i < length; i++) { 
=======
   for (i = 0; i < length; i++) {
>>>>>>> df469005905ebb90ce107e9ee9622e19e39faae3
    var $ppc = $('.pie-chart-'+i),
    percent = $ppc.data('percent'),
    deg = 360*percent/100;
    if (percent > 50) {
      $ppc.addClass('gt-50');
    }
    $('.ppc-progress-fill-'+i).css('transform','rotate('+ deg +'deg)');
    $('.ppc-percents-span-'+i).html(percent+'%');
  }
});