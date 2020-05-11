function nextImg() {
    $.ajax({
        type: 'get',
        url: "/login/getCaptcheCodeImg",
        success: function (data) {
            document.getElementById("captcheImg").src = "/login/getCaptcheCodeImg?t=" + new Date().getTime();
        }
    })
}

$(function () {
    $("#captcheImg").click(function () {
        nextImg();
    });
    $("#login_request").click(function () {
        var dat = $("#form_dat").serialize();
        $.ajax({
            type: 'post',
            url: '/login/login',
            data: dat,
            dataType: 'json',
            success: function (data) {
                if (data.state === "success") {
                    if (data.msg === "admin")
                        window.location.href = "/admin";
                    if (data.msg === "borrower")
                        window.location.href = "/borrower";
                } else {
                    alert(data.msg);
                    nextImg();
                }
            }
        })
    });
    $("#reset_login").click(function resetLoginInf() {
        document.getElementById("input_accout").value = "";
        document.getElementById("input_psw").value = "";
        document.getElementById("input_captche").value = "";
    });
});

