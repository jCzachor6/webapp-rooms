package czachor.jakub.rooms.controller;

import czachor.jakub.rooms.utils.command.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller("messageController")
public class MessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message send(Message message) {
        Message echo = new Message();
        echo.setFrom(message.getFrom());
        echo.setLine("Echo: " + message.getLine());
        System.out.println(echo);
        return echo;
    }
}
