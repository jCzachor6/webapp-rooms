package czachor.jakub.rooms.controller;

import czachor.jakub.rooms.service.CommandService;
import czachor.jakub.rooms.utils.command.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller("messageController")
public class MessageController {

    private final CommandService commandService;

    @Autowired
    public MessageController(CommandService commandService) {
        this.commandService = commandService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message send(Message message) {
        if (message.isCommand()) {
            return commandService.resolve(message).process();
        } else {
            return message;
        }
    }
}
