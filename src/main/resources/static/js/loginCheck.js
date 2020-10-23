

function hiddenError () {
    var errorMsg = document.getElementById("ErrorMsg");
    errorMsg.hidden = true;
}
function subCheck () {
    // alert("changed");
    var errorMsg = document.getElementById("ErrorMsg");
    var nameBox = document.getElementsByName("uname")[0];
    var passBox = document.getElementsByName("upass")[0];
    // if (passBox.value !== "" && nameBox.value !== "") {
        document.getElementById("subform").submit();
    // } else {
    //     errorMsg.innerHTML = "账号或密码不能为空";
    //     errorMsg.hidden = false;
    // }
}
