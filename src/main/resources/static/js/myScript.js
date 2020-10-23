var i = 1;
var imgs = document.getElementsByClassName("item");
$('.container:first').css("background-color", "rgb(51, 116, 189)");
$('.list-group').find("a").css("color", "rgb(86,153,192)");
$('h2:first').css("color", "white");
$('span:first').css("color", "white");
$("span:eq(1)").css("color", "red");
function decImg() {
    if (i >= 1) {
        imgs[(i - 1) < 0 ? 2 : (i - 1) % 3].className = "item active";
        imgs[i % 3].className = "item";
        i--;
    }
}

function addImg() {
    imgs[(i - 1) < 0 ? 2 : (i - 1) % 3].className = "item";
    imgs[i % 3].className = "item active";
    i++;
}

function changetrs() {
    var backcolors = ['warning','success'];
    var trs = document.getElementsByTagName("tr");
    if (trs!=null&&trs.length!==0){
        console.log("it is empty");
    }
    for(let bac=0;bac<trs.length;bac++){
        console.log(backcolors.length);
        trs[bac].className = backcolors[bac%backcolors.length];
    }
}


