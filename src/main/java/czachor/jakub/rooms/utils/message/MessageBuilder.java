package czachor.jakub.rooms.utils.message;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MessageBuilder {
    private String from;
    private String line;
    private MessageType type;
    private Destination.Target target;
    private String targetName;

    public Message build() {
        Message message = new Message();
        message.setFrom(from);
        message.setLine(line);
        message.setType(type);
        message.setDestination(new Destination(target, targetName));
        return message;
    }

    public List<Message> buildAsSingletonList() {
        return Collections.singletonList(build());
    }

    public MessageBuilder orginal(Message message) {
        from = Objects.toString(message.getFrom(), "");
        line = Objects.toString(message.getLine(), "");
        type = message.getType();
        if (message.getDestination() != null) {
            target = message.getDestination().getTarget();
            targetName = message.getDestination().getTargetName();
        }
        return this;
    }

    public MessageBuilder from(String from) {
        this.from = from;
        return this;
    }

    public MessageBuilder line(String line) {
        this.line = line;
        return this;
    }

    public MessageBuilder type(MessageType type) {
        this.type = type;
        return this;
    }

    public MessageBuilder target(Destination.Target target) {
        this.target = target;
        return this;
    }

    public MessageBuilder targetName(String targetName) {
        this.targetName = targetName;
        return this;
    }
}
