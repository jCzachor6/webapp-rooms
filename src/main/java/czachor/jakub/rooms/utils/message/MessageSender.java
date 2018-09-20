package czachor.jakub.rooms.utils.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageSender {
    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public MessageSender(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void send(List<Message> messages, String roomKey) {
        messages.forEach(message -> {
            Destination.Target target = message.getDestination().getTarget();
            switch (target) {
                case ROOM:
                    sendToRoom(message);
                    break;
                case USER:
                    sendToUser(message, roomKey);
                    break;
            }
        });
    }

    public void send(Message message){
        String roomKey = message.getDestination().getTargetName();
        messagingTemplate.convertAndSend(String.format("/room/%s", roomKey), message);
    }

    private void sendToUser(Message message, String roomKey) {
        messagingTemplate.convertAndSendToUser(
                message.getDestination().getTargetName(), String.format("/room/%s", roomKey), message);
    }

    private void sendToRoom(Message message) {
        messagingTemplate.convertAndSend(
                String.format("/room/%s", message.getDestination().getTargetName()), message);
    }
}
