package czachor.jakub.rooms.service;


import czachor.jakub.rooms.utils.command.AbstractCommand;
import czachor.jakub.rooms.utils.message.Message;

public interface CommandService {
    AbstractCommand resolve(Message message);
}
