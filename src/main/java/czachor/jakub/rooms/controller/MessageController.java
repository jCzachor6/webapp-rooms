package czachor.jakub.rooms.controller;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.service.CommandService;
import czachor.jakub.rooms.service.StatisticsService;
import czachor.jakub.rooms.utils.WebsocketUser;
import czachor.jakub.rooms.utils.message.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Properties;

@Controller("messageController")
public class MessageController {
    private final CommandService commandService;
    private final StatisticsService statisticsService;
    private final WebsocketUser user;
    private final MessageSender sender;

    @Autowired
    public MessageController(CommandService commandService,
                             StatisticsService statisticsService, WebsocketUser user, MessageSender sender) {
        this.commandService = commandService;
        this.statisticsService = statisticsService;
        this.user = user;
        this.sender = sender;
    }

    @MessageMapping("/chat/{roomkey}")
    public void send(@DestinationVariable(value = "roomkey") String roomKey,
                     @Payload Message original,
                     SimpMessageHeaderAccessor accessor) {
        String sessionId = getSessionId(accessor);
        MessageProcessHelper helper = new MessageProcessHelper(user, roomKey, sessionId);

        if (helper.isMessageCommand(original)) {
            List<Message> messages = commandService
                    .resolve(original)
                    .process(helper);
            sender.send(messages, roomKey);
        } else {
            Message message = new MessageBuilder()
                    .orginal(original)
                    .type(MessageType.NORMAL)
                    .from(user.getUsername())
                    .build();
            sender.send(message);
        }
    }

    private String getSessionId(SimpMessageHeaderAccessor accessor) {
        return accessor.getSessionAttributes().get(Consts.SESSION_ID).toString();
    }

    @RequestMapping("/session")
    @ResponseBody
    public Properties getSessionId(HttpSession session) {
        Properties properties = new Properties();
        properties.setProperty("sessionId", session.getId());
        return properties;
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
