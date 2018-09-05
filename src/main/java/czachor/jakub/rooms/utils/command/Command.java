package czachor.jakub.rooms.utils.command;

import czachor.jakub.rooms.exceptions.NotEnoughCommandParameters;
import czachor.jakub.rooms.exceptions.TooManyCommandParameters;
import czachor.jakub.rooms.utils.message.Message;
import lombok.Data;

import java.util.List;

@Data
public abstract class Command {
    private CommandType type;
    protected List<String> details;

    public Command(List<String> details) {
        this.details = details;
    }

    private void throwOnExceedingParameters(){
        if(details.size()>type.getMaxParameters()){
            throw new TooManyCommandParameters("This command requires maximum " + type.getMaxParameters() + " parameters");
        }
        if(details.size()<type.getMinParameters()){
            throw new NotEnoughCommandParameters("This command requires minimum " + type.getMinParameters() + " parameters");
        }
    }

    protected void setType(CommandType type) {
        this.type = type;
        this.throwOnExceedingParameters();
    }

    protected void buildIndexedLine(StringBuilder builder, int index, String content) {
        builder.append(index + 1);
        builder.append(". ");
        builder.append(content);
        builder.append("\n");
    }

    public abstract Message process(String from, String roomkey);
}
