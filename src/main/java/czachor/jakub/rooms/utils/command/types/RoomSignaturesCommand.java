package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;

import java.util.List;

public class RoomSignaturesCommand extends Command {
    public RoomSignaturesCommand(List<String> details) {
        super(details);
        setType(CommandType.ROOM_SIGNATURES);
    }

    @Override
    public Message process(String from, String roomkey) {
        //TODO
        return null;
    }
}
