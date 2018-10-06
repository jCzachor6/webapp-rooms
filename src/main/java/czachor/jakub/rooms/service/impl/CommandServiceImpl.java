package czachor.jakub.rooms.service.impl;

import czachor.jakub.rooms.service.CommandService;
import czachor.jakub.rooms.utils.command.AbstractCommand;
import czachor.jakub.rooms.utils.command.CommandDetailsLoader;
import czachor.jakub.rooms.utils.command.CommandsResolver;
import czachor.jakub.rooms.utils.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commandService")
public class CommandServiceImpl implements CommandService {
    private final CommandsResolver commandsResolver;

    @Autowired
    public CommandServiceImpl(CommandsResolver commandsResolver) {
        this.commandsResolver = commandsResolver;
    }

    @Override
    public AbstractCommand resolve(Message message) {
        return retrieveSpecificCommand(message);
    }

    private AbstractCommand retrieveSpecificCommand(Message message) {
        CommandDetailsLoader loader = new CommandDetailsLoader(message.getLine());
        return this.commandsResolver.getCommand(loader);
    }
}
