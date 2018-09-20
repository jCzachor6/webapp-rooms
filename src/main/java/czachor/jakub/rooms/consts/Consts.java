package czachor.jakub.rooms.consts;

import java.util.Arrays;
import java.util.List;

public interface Consts {
    String BOT_NAME = "Chat bot";
    List<String> WORKING_COMMANDS = Arrays.asList(
            "/help - show list of commands. ",
            "/echo \"text\" - get echo. ",
            "/roll - get random value from 0-100. "
    );
    String NEW_USER_NAME = "anon";
    String CHANGE_USER_NAME_REGEX = NEW_USER_NAME + "[0-9]*";
    String SESSION_ID = "sessionId";
}
