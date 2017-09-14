
function showDiv() {


   var div = document.getElementById('fragment');
       if (div.style.display !== 'none') {
           div.style.display = 'none';
       }
       else {
           div.style.display = 'block';
       }
}


$(function () {
    $('#datepicker1').datetimepicker(({
        format: 'DD/MM/YYYY'
    }));
    $('#datepicker2').datetimepicker({
        format: 'DD/MM/YYYY',
        useCurrent: false //Important! See issue #1075
    });
    $('#timepicker1').datetimepicker(({
        format: 'HH:mm',
        defaultDate:moment(new Date()).hours(6).minutes(0).seconds(0).milliseconds(0)
    }));
    $('#timepicker2').datetimepicker({
        format: 'HH:mm',
        defaultDate:moment(new Date()).hours(18).minutes(0).seconds(0).milliseconds(0)

    });

    $("#datepicker1").on("dp.change", function (e) {
        $('#datepicker2').data("DateTimePicker").minDate(e.date);
    });
    $("#datepicker2").on("dp.change", function (e) {
        $('#datepicker1').data("DateTimePicker").maxDate(e.date);
    });

});
