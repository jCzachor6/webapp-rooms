package czachor.jakub.rooms.utils.command;

import czachor.jakub.rooms.utils.annotation.Command;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class CommandsResolver{
    private Map<String, Class<? extends AbstractCommand>> availableCommands;
    private ApplicationContext context;

    public CommandsResolver(Map<String, Class<? extends AbstractCommand>> availableCommands,
                            ApplicationContext applicationContext) {
        this.availableCommands = availableCommands;
        this.context = applicationContext;
    }

    public AbstractCommand getCommand(CommandDetailsLoader loader){
        Class<? extends AbstractCommand> c = availableCommands.get(loader.getCommand());
        Command annotation = c.getAnnotation(Command.class);
        try {
            AbstractCommand abstractCommand = c.newInstance();
            FieldUtils.writeField(abstractCommand, "maxParameters", annotation.maxParameters(), true);
            FieldUtils.writeField(abstractCommand, "detailsLoader", loader, true);
            MethodUtils.invokeMethod(abstractCommand, true, "setLoaderMaxParams");
            writeDaoFields(abstractCommand, annotation);
            return abstractCommand;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void writeDaoFields(AbstractCommand abstractCommand, Command command) throws IllegalAccessException {
        for (int i = 0; i < command.beans().length; i++) {
            String daoName = StringUtils.uncapitalize(command.beans()[i].getSimpleName());
            FieldUtils.writeField(abstractCommand, daoName, getDao(daoName), true);
        }
    }

    private Object getDao(String name) {
        return context.getBean(name);
    }
}
