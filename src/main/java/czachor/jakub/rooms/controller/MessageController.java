package czachor.jakub.rooms.controller;

import czachor.jakub.rooms.service.CommandService;
import czachor.jakub.rooms.service.StatisticsService;
import czachor.jakub.rooms.utils.WebsocketUser;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;
import czachor.jakub.rooms.utils.message.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller("messageController")
public class MessageController {
    private final CommandService commandService;
    private final StatisticsService statisticsService;
    private final SimpMessageSendingOperations messagingTemplate;
    private final WebsocketUser user;

    @Autowired
    public MessageController(CommandService commandService,
                             SimpMessageSendingOperations messagingTemplate, StatisticsService statisticsService, WebsocketUser user) {
        this.commandService = commandService;
        this.messagingTemplate = messagingTemplate;
        this.statisticsService = statisticsService;
        this.user = user;
    }

    @MessageMapping("/chat/{roomkey}")
    public void send(@DestinationVariable(value = "roomkey") String roomKey,
                     @Payload Message original) {

        MessageProcessHelper helper = new MessageProcessHelper(user, roomKey);
        Message returnMessage;
        if (original.isCommand()) {
            returnMessage = commandService.resolve(original).process(helper);
        } else {
            returnMessage = buildNormalMessage(original, helper);
        }

        String targetName = helper.getDestination().getTargetName();
        switch (helper.getDestination().getTarget()) {
            case ROOM:
                messagingTemplate.convertAndSend(String.format("/room/%s", targetName), returnMessage);
                break;
            case USER:
                messagingTemplate.convertAndSendToUser(targetName, String.format("/room/%s", roomKey), returnMessage);
                break;
        }
    }

    private Message buildNormalMessage(Message edit, MessageProcessHelper helper) {
        edit.setType(MessageType.NORMAL);
        edit.setFrom(helper.getUser().getUsername());
        return edit;
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
