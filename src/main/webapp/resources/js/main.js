'use strict';

var input;
var stompClient = null;
var currentSubscription = null;

var nickname;
var roomKey;

function init() {
    loadRoomKey();
    loadInput();
    loadNickname();
    disconnect();
    connect();
}

function newMessage(input) {
    var split = input.split("\n");
    split.forEach(line => {
        var p = document.createElement("p");
        var node = document.createTextNode(line);
        p.appendChild(node);
        var element = document.getElementById("chat");
        element.appendChild(p);
    });
}

function connect() {
    var socket = new SockJS('/jczachor-web-app-rooms/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    var topic = '/app/chat/' + roomKey;
    if (currentSubscription) {
        currentSubscription.unsubscribe();
    }
    currentSubscription = stompClient.subscribe('/room/' + roomKey, onMessageReceived);
    stompClient.send(topic, {}, JSON.stringify({from: nickname, line: '/connect', roomKey: roomKey}));
}

function onError(error) {
    newMessage('Could not connect to WebSocket server. Please refresh this page to try again!')
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    newMessage(message.line)
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
}

function loadRoomKey() {
    roomKey = getParameterByName('key')
}

function sendMessage(value) {
    var topic = '/app/chat/' + roomKey;
    stompClient.send(topic, {}, JSON.stringify({from: nickname, line: value, roomKey: roomKey}));
}

function loadInput() {
    input = document.getElementById("input");
    input.addEventListener("keyup", function (event) {
        event.preventDefault();
        if (event.keyCode === 13) {
            if (input.value.length > 0) {
                sendMessage(input.value);
                input.value = "";
            }
        }
    });
}

function loadNickname() {
    var client = new HttpClient();
    client.get('http://localhost:8081/jczachor-web-app-rooms/stat/total', function (response) {
        var stat = JSON.parse(response);
        nickname = 'anon' + stat.value;
    });
}