package czachor.jakub.rooms.controller;

import czachor.jakub.rooms.utils.command.Message;
import czachor.jakub.rooms.utils.command.StringCommandResolver;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller("messageController")
public class MessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message send(Message message) {
        if(message.isCommand()){
            return new StringCommandResolver()
                    .resolve(message)
                    .process();
        } else return message;
    }
}
