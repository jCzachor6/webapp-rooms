'use strict';

var stompClient = null;
var username = null;
connect();

var input = document.getElementById("input");
input.addEventListener("keyup", function (event) {
    event.preventDefault();
    if (event.keyCode === 13) {
        if (input.value.length > 0) {
            sendMessage(input.value);
            newMessage(input.value);
            input.value = "";
        }
    }
});

function newMessage(input) {
    var p = document.createElement("p");
    var node = document.createTextNode(input);
    p.appendChild(node);
    var element = document.getElementById("chat");
    element.appendChild(p);
}

function connect() {
    var socket = new SockJS('/jczachor-web-app-rooms/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function(messageOutput) {
            showMessageOutput(JSON.parse(messageOutput.body));
        });
    });
}

function sendMessage() {
    username = "tyry";
    stompClient.send("app/chat", {},
        JSON.stringify({'from':username, 'line':input.value}));
}

function showMessageOutput(messageOutput) {
    newMessage(messageOutput.line);
}