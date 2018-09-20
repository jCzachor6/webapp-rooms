package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;

import java.util.List;

public class RoomSignaturesCommand extends Command {
    public RoomSignaturesCommand(List<String> details) {
        super(CommandType.ROOM_SIGNATURES, details);
    }

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        //TODO
        return null;
    }
}
