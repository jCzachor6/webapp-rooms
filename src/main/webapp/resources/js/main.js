'use strict';
var input;

function init() {
    input = document.getElementById("input");
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
    disconnect();
    connect();
}

function newMessage(input) {
    var p = document.createElement("p");
    var node = document.createTextNode(input);
    p.appendChild(node);
    var element = document.getElementById("chat");
    element.appendChild(p);
}

var stompClient = null;

function connect() {
    disconnect();
    var socket = new SockJS('/jczachor-web-app-rooms/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function(messageOutput) {
            newMessage(JSON.parse(messageOutput.body).line);
        });
    });
}

function disconnect() {
    if(stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendMessage(line) {
    var from = "tyry";
    stompClient.send("/app/chat", {},
        JSON.stringify({'from':from, 'line':line}));
}