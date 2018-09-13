package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;
import czachor.jakub.rooms.utils.message.MessageType;

import java.util.List;

public class ConnectCommand extends Command {
    public ConnectCommand(List<String> details) {
        super(CommandType.CONNECT, details);
    }

    @Override
    public Message process(MessageProcessHelper helper) {
        if(details.isEmpty()){
            return onEmptyDetails(helper);
        }else{
            return newNickname(helper);
        }
    }

    private Message onEmptyDetails(MessageProcessHelper helper){
        Message message = new Message(Consts.BOT_NAME, MessageType.JOIN);
        message.setLine("Hello there! " + helper.getNickname() + "\n Type '/help' to see possible commands. ");
        return message;
    }

    private Message newNickname(MessageProcessHelper helper){
        String newNickname = details.get(0);
        Message message = new Message(Consts.BOT_NAME, MessageType.JOIN);
        String line = helper.getNickname() + " changed his nickname to " + newNickname + ". ";
        helper.getPrincipal().setNickname(newNickname);
        message.setLine(line);
        return message;
    }
}
