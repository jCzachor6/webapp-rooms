package czachor.jakub.rooms.utils.command;

import czachor.jakub.rooms.exceptions.NotEnoughCommandParameters;
import czachor.jakub.rooms.exceptions.TooManyCommandParameters;
import lombok.Data;

import java.util.List;

@Data
public abstract class Command {
    protected CommandType type;
    protected String author;
    protected List<String> details;
    protected int MAX_PARAMETERS;
    protected int MIN_PARAMETERS;

    public Command(String author, List<String> details) {
        this.author = author;
        this.details = details;
    }

    protected void throwOnExceedingParameters(){
        if(details.size()>MAX_PARAMETERS){
            throw new TooManyCommandParameters("This command requires maximum " + MAX_PARAMETERS + " parameters");
        }
        if(details.size()<MIN_PARAMETERS){
            throw new NotEnoughCommandParameters("This command requires minimum " + MIN_PARAMETERS + " parameters");
        }
    }
}
