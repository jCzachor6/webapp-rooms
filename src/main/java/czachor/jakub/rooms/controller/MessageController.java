package czachor.jakub.rooms.controller;

import czachor.jakub.rooms.utils.command.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message send(Message message) {
        Message echo = new Message("Echo: " + message.getLine(), message.getFrom());
        System.out.println(echo);
        return echo;
    }
}
