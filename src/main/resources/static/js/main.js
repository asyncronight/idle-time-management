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
        $('#datetimepicker6').datetimepicker(({
        	format: 'd/MM/YYYY, h:mm',
        }));
        $('#datetimepicker7').datetimepicker({
        	format: 'd/MM/YYYY, h:mm',
            useCurrent: false //Important! See issue #1075
        });
        $("#datetimepicker6").on("dp.change", function (e) {
            $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
        });
        $("#datetimepicker7").on("dp.change", function (e) {
            $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
        });
    });