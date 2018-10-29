/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('.sidenav').sidenav();
});

$('.dropdown-trigger').dropdown();

$(document).ready(function () {
    $('.modal').modal();
});

$('[data-fancybox="gallery"]').fancybox({
    buttons: [
        "zoom",
        "slideShow",
        "fullScreen",
        "download",
        "thumbs",
        "close"
    ]
});

function  sair() {
    var form = document.querySelector("#sair");
    form.submit();
}




