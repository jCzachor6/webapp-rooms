'use strict';

var input = document.getElementById("input");
input.addEventListener("keyup", function(event) {
    event.preventDefault();
    if (event.keyCode === 13) {
        if(input.value.length>0){
            newMessage(input.value);
            input.value = "";
        }
    }
});

function newMessage(message) {
    var p = document.createElement("p");
    var node = document.createTextNode(message);
    p.appendChild(node);
    var element = document.getElementById("chat");
    element.appendChild(p);
}

