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
                console.log(data.retCode)
            }
        });
    });
});