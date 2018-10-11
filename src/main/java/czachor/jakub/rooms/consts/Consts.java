package czachor.jakub.rooms.consts;

import java.util.Arrays;
import java.util.List;

public interface Consts {
    List<String> WORKING_COMMANDS = Arrays.asList(
            "/help - show list of commands. ",
            "/connect <nickname> - change your username. ",
            "/pm <username> <text> - send private message. ",
            "/roll - get random value from 0-100. ",
            "/flip - flip coin. ",
            "/echo <text> - get echo. ",
            "/addsignature <text> - sign anything to room you are currently in. ",
            "/allusers - get list of all users. ",
            "/roomsignatures - get all signatures on room you are currently in. ",
            "/userinfo <username> - user details. ",
            "/usersignatures <username> - get user all signatures. "
    );

    String BOT_NAME = "Chat bot";
    String NEW_USER_NAME = "anon";

    String CHANGE_USER_NAME_REGEX = NEW_USER_NAME + "[0-9]*";

    String SESSION_ID = "sessionId";
}
