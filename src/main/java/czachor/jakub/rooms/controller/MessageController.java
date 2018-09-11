package czachor.jakub.rooms.controller;

import czachor.jakub.rooms.service.CommandService;
import czachor.jakub.rooms.service.StatisticsService;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller("messageController")
public class MessageController {
    private final CommandService commandService;
    private final StatisticsService statisticsService;
    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public MessageController(CommandService commandService,
                             SimpMessageSendingOperations messagingTemplate, StatisticsService statisticsService) {
        this.commandService = commandService;
        this.messagingTemplate = messagingTemplate;
        this.statisticsService = statisticsService;
    }

    @MessageMapping("/chat/{roomkey}")
    public void send(@DestinationVariable(value = "roomkey") String roomKey,
                     @Payload Message message,
                     SimpMessageHeaderAccessor headerAccessor) {

        String nickname = headerAccessor.getSessionAttributes().get("nickname").toString();
        Message returnMessage;
        if (message.isCommand()) {
            returnMessage = commandService.resolve(message).process(nickname, roomKey);
        } else {
            returnMessage = message;
            returnMessage.setType(MessageType.NORMAL);
            returnMessage.setFrom(nickname);
        }
        messagingTemplate.convertAndSend(String.format("/room/%s", roomKey), returnMessage);
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        statisticsService.incrementActiveUsers();
        statisticsService.incrementTotalJoinedUsers();
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        statisticsService.decrementActiveUsers();
    }
}
