package czachor.jakub.rooms.utils.command;

import czachor.jakub.rooms.exceptions.NotEnoughCommandParameters;
import czachor.jakub.rooms.exceptions.TooManyCommandParameters;
import lombok.Data;

import java.util.List;

@Data
public abstract class Command {
    private CommandType type;
    protected String author;
    protected List<String> details;

    public Command(String author, List<String> details) {
        this.author = author;
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
}
