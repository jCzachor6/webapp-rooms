package czachor.jakub.rooms.utils.command;

import czachor.jakub.rooms.exceptions.NotEnoughCommandParameters;
import czachor.jakub.rooms.exceptions.TooManyCommandParameters;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;

import java.util.List;

public abstract class Command {
    private CommandType type;
    protected List<String> details;

    public Command(CommandType type, List<String> details) {
        this.details = details;
        this.type = type;
        this.throwOnExceedingParameters();
    }

    private void throwOnExceedingParameters(){
        if(details.size()>type.getMaxParameters()){
            throw new TooManyCommandParameters("This command requires maximum " + type.getMaxParameters() + " parameters");
        }
        if(details.size()<type.getMinParameters()){
            throw new NotEnoughCommandParameters("This command requires minimum " + type.getMinParameters() + " parameters");
        }
    }

    protected void buildIndexedLine(StringBuilder builder, int index, String content) {
        builder.append(index + 1);
        builder.append(". ");
        builder.append(content);
        builder.append("\n");
    }

    public abstract Message process(MessageProcessHelper helper);
}
