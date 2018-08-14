$(function() {
    for(var i = 2; i < 13; i ++) {
        var tr1 = $('#bodyT').find('tr').eq(0).clone();
        tr1.find('td').eq(0).text(i)
        $('#bodyT').append(tr1);
    }
});