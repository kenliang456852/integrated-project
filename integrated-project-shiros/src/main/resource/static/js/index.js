$(function() {

    for(var i = 2; i < 13; i ++) {
        var tr1 = $('#bodyT').find('tr').eq(0).clone();
        tr1.find('td').eq(0).text(i)
        $('#bodyT').append(tr1);
    }
    $("input[name='isJump']").bind("click", function() {
        if(this.checked) {
            $(this).parent().parent().attr("bgcolor","#E4E4E4");
            $(this).parent().parent().find('select').eq(1).show();

        } else {
            $(this).parent().parent().attr("bgcolor","white");
            $(this).parent().parent().find('select').eq(1).hide();
        }
    });

    $('#ddlLine').combotree({
        valueField: "id", //Value字段
        textField: "text", //Text字段
        multiple: true,
        data: [{ "id": 1, "text": "All", "children": [{ "id": 13, "text": "C1" }, { "id": 14, "text": "C2" }, { "id": 15, "text": "C3"}]}]
        ,
        //                url: "tree_data2.json", //数据源
        onCheck: function (node, checked) {
            //让全选不显示
            $("#ddlLine").combotree("setText", $("#ddlLine").combobox("getText").toString().replace("全选,", ""));
        },
        onClick: function (node, checked) {
            //让全选不显示
            $("#ddlLine").combotree("setText", $("#ddlLine").combobox("getText").toString().replace("全选,", ""));
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
function changeFild() {
    var currentIdentity = $("#currentIdentity").val();
    var currentLocation = $("#currentLocation").val();
    if(currentIdentity && currentLocation) {
        $('#bodyT').find("tr").eq(currentLocation-1).find('select').last().val(currentIdentity);
    }
}
