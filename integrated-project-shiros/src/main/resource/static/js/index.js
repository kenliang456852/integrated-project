$(function() {
    for(var i = 2; i < 13; i ++) {
        var tr1 = $('#bodyT').find('tr').eq(0).clone();
        tr1.find('td').eq(0).text(i)
        $('#bodyT').append(tr1);
    }
    $("input[name='isJump']").bind("click", function() {
        if(this.checked) {
            $(this).parent().parent().attr("bgcolor","#E4E4E4");
        } else {
            $(this).parent().parent().attr("bgcolor","white");
        }
    });
});
function flushr() {
    $('#flushdiv').find('select').val("");
    console.log($('#flushdiv').find("input[type='checkbox']"));

    $('#flushdiv').find("input[type='checkbox']").each(function(index,element) {
        if(element.checked) {
            element.click();
        }
    });
}
