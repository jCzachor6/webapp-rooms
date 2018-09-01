package czachor.jakub.rooms.service;


import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.Message;

public interface CommandService {
    Command resolve(Message message);
}
