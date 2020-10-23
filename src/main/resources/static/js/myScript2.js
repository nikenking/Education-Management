function showbox() //显示隐藏层和弹出层
{
    var hideobj = document.getElementById("hidebg");
    hidebg.style.display = "block"; //显示隐藏层
    hidebg.style.height = document.body.clientHeight + "px"; //设置隐藏层的高度为当前页面高度
    document.getElementById("hidebox").style.display = "block"; //显示弹出层
}

function hidebox() //去除隐藏层和弹出层
{
    document.getElementById("hidebg").style.display = "none";
    document.getElementById("hidebox").style.display = "none";
}

function contact() {
    document.getElementById("hidebg").style.display = "none";
    document.getElementById("hidebox").style.display = "none";
    window.open("url");
}