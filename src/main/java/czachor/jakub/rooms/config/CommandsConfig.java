package czachor.jakub.rooms.config;

import czachor.jakub.rooms.utils.annotation.Command;
import czachor.jakub.rooms.utils.command.AbstractCommand;
import czachor.jakub.rooms.utils.command.CommandsResolver;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CommandsConfig implements ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Bean
    public CommandsResolver commandsResolver(){
        return new CommandsResolver(availableCommands(), this.context);
    }

    @Bean
    public Map<String, Class<? extends AbstractCommand>> availableCommands(){
        Map<String, Class<? extends AbstractCommand>> commands = new HashMap<>();
        Reflections ref = new Reflections("czachor.jakub.rooms.utils.command.types");
        for (Class<?> c : ref.getTypesAnnotatedWith(Command.class)) {
            Command command = c.getAnnotation(Command.class);
            commands.put(command.name(), (Class<? extends AbstractCommand>) c);
        }
        return commands;
    }
}
