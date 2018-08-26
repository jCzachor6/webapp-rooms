package czachor.jakub.rooms.utils.command;

import czachor.jakub.rooms.exceptions.NoSuchCommandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCommandResolver {
    private String line;

    public Command resolve(StringCommand stringCommand) {
        this.line = stringCommand.getLine();
        Command command = new Command();
        command.setType(retrieveCommandType());
        command.setDetails(retrieveDetails());
        return command;
    }

    private CommandType retrieveCommandType() {
        String afterSlash = line.substring(1, line.indexOf(" "));
        switch (afterSlash) {
            case "tip":
            case "help":
            case "info":
            case "?":
            case "??":
            case "???":
                return CommandType.TIP;
        }
        throw new NoSuchCommandException("Command \'" + afterSlash + "\' doesn't exist");
    }

    private List<String> retrieveDetails() {
        List<String> details = Arrays.asList(line.split("\""));
        List<String> detailsNoWhitespaces = new ArrayList<>();
        for (String s : details) {
            boolean shouldAdd = !isMadeOfWhiteSpaces(s);
            if (shouldAdd) {
                detailsNoWhitespaces.add(s);
            }
        }
        return detailsNoWhitespaces;
    }

    private boolean isMadeOfWhiteSpaces(String string) {
        boolean isSpace = false;
        for (char c : string.toCharArray()) {
            if (c == ' ') {
                isSpace = true;
                break;
            }
        }
        return isSpace;
    }
}
