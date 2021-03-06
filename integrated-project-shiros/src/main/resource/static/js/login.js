$(function () {
    $('#login_btn').click(function () {
        var jsonRequest = {
            reqHeader: {
                sid: 1
            },
            reqBody: {
                userName: $('#userName').val(),
                password: $('#password').val()
            }
        };
        jsonRequest = JSON.stringify(jsonRequest)
        console.log(jsonRequest);
        $.ajax({
            type: 'POST',
            url: '/login',
            data: jsonRequest,
            dataType: 'JSON',
            contentType: 'application/json;charset=utf-8',
            async:true,
            beforeSend: function() {
            },
            success: function (data) {
                console.log(data.retCode);
                if(data && data.retCode ==='0000000') {
                    window.location.href = $ctx + "index.html";
                } else if(data) {
                    alert(data.retDesc);
                } else {
                    alert("服务器异常！请联系管理员。")
                }
            }
        });
    });
});