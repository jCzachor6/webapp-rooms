package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.config.ActiveUsers;
import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.annotation.Command;
import czachor.jakub.rooms.utils.command.AbstractCommand;
import czachor.jakub.rooms.utils.message.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Command(maxParameters = 2, name = "pm", daos = {ActiveUsers.class})
public class PrivateMessage extends AbstractCommand {
    @Override
    public List<Message> process(MessageProcessHelper helper) {
        String targetSessionId = findUserSessionId(firstParam());
        if (targetSessionId != null) {
            return sendPrivateMessage(helper, targetSessionId);
        } else {
            return sendUserNotFoundMessage(helper);
        }
    }

    private String findUserSessionId(String username) {
        return activeUsers.getSessionIdByUsername(username);
    }

    private List<Message> sendUserNotFoundMessage(MessageProcessHelper helper) {
        return new MessageBuilder()
                .from(Consts.BOT_NAME)
                .target(Destination.Target.USER)
                .targetName(helper.getSessionId())
                .type(MessageType.COMMAND)
                .line("User: " + StringUtils.wrap(firstParam(), '"') + " does not exists.")
                .buildAsSingletonList();
    }

    private List<Message> sendPrivateMessage(MessageProcessHelper helper, String targetSessionId) {
        MessageBuilder builder = new MessageBuilder()
                .from(helper.getUser().getUsername())
                .target(Destination.Target.USER)
                .targetName(targetSessionId)
                .type(MessageType.PRIVATE)
                .line(secondParam());
        List<Message> messages = new ArrayList<>(2);
        messages.add(builder.build());
        Message self = builder.targetName(helper.getSessionId()).build();
        messages.add(self);
        return messages;
    }
}
