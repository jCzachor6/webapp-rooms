package czachor.jakub.rooms.consts;

import java.util.Arrays;
import java.util.List;

public interface Consts {
    public static final String BOT_NAME = "Chat bot";
    public static final List<String> WORKING_COMMANDS = Arrays.asList(
            "/help - show list of commands. ",
            "/echo \"text\" - get echo. ",
            "/roll - get random value from 0-100. "
    );
    public static final String NEW_USER_NAME = "anon";
}
