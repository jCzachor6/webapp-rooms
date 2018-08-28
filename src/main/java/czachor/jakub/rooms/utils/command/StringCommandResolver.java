package czachor.jakub.rooms.utils.command;

import czachor.jakub.rooms.exceptions.NoSuchCommandException;
import czachor.jakub.rooms.utils.command.types.TipCommand;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StringCommandResolver {
    private Message message;

    public Command resolve(Message message) {
        this.message = message;
        return retrieveSpecificCommand();
    }

    private Command retrieveSpecificCommand() {
        String afterSlash = message.getLine().substring(1, getIndexOfFirstSpaceOrEnd());
        switch (afterSlash) {
            case "tip":
            case "help":
            case "info":
            case "?":
            case "??":
            case "???":
                return new TipCommand(message.getFrom(), retrieveDetails());
        }
        throw new NoSuchCommandException("Command \'" + afterSlash + "\' doesn't exist");
    }

    private List<String> retrieveDetails() {
        String[] details = message.getLine().split("\"");
        List<String> detailsNoWhitespaces = new ArrayList<>();
        for (int i = 1 /*ignore first*/; i<details.length; i++) {
            boolean shouldAdd = !StringUtils.isWhitespace(details[i]);
            if (shouldAdd) {
                detailsNoWhitespaces.add(details[i]);
            }
        }
        return detailsNoWhitespaces;
    }

    private int getIndexOfFirstSpaceOrEnd(){
        String s = message.getLine();
        if(s.contains(" ")){
            return s.indexOf(" ");
        }else{
            return s.length();
        }
    }
}
