'use strict';

var input;
var stompClient = null;
var currentSubscription = null;
var selfSubscription = null;

var nickname;
var roomKey;

function init() {
    loadRoomKey();
    loadInput();
    disconnect();
    connect();
}

function newMessage(message) {
    var author = message.from;
    var split = message.line.split("\n");
    split.forEach(line => {
        var p = document.createElement("p");
        var node = document.createTextNode(author + ': ' + line);
        p.appendChild(node);
        p.style.color = message.type.color;
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
    subscribeToRoom();
    subscribeToSelf();
}

function subscribeToRoom() {
    if (currentSubscription) {
        currentSubscription.unsubscribe();
    }
    currentSubscription = stompClient.subscribe('/room/' + roomKey, onMessageReceived);
}

function subscribeToSelf() {
    var client = new HttpClient();
    client.get('http://localhost:8081/jczachor-web-app-rooms/session', function (response) {
        var session = JSON.parse(response);
        if (selfSubscription) {
            selfSubscription.unsubscribe();
        }
        selfSubscription = stompClient.subscribe('/user/' + session.sessionId + '/room/' + roomKey, onMessageReceived);
        var topic = '/app/chat/' + roomKey;
        stompClient.send(topic, {}, JSON.stringify({from: nickname, line: '/connect', roomKey: roomKey}));
    });
}

function onError(error) {
    var red = {
        color: '#d11f1f'
    };
    var message = {
        line: 'Could not connect to WebSocket server. Please refresh this page to try again!',
        from: 'error: ',
        room: roomKey,
        type: red
    };
    newMessage(message)
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    newMessage(message)
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
    stompClient.send(topic, {}, JSON.stringify({line: value, roomKey: roomKey}));
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