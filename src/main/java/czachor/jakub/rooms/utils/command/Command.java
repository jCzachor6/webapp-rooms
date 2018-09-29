package czachor.jakub.rooms.utils.command;

import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;

import java.util.List;

public abstract class Command {
    private CommandDetailsLoader loader;
    private String firstParam;
    private String secondParam;
    private String lastParam;

    public Command(int maxParameters, CommandDetailsLoader loader) {
        this.loader = loader;
        this.loader.with(maxParameters);
    }

    protected String firstParam(){
        if(this.firstParam == null){
            this.firstParam = loader.details().pickFirst();
        }
        return this.firstParam;
    }

    protected String secondParam(){
        if(this.secondParam == null){
            this.secondParam = loader.details().pickSecond();
        }
        return this.secondParam;
    }

    protected String lastParam(){
        if(this.lastParam == null){
            this.lastParam = loader.details().pickLast();
        }
        return this.lastParam;
    }

    protected void buildIndexedLine(StringBuilder builder, int index, String content) {
        builder.append(index + 1);
        builder.append(". ");
        builder.append(content);
        builder.append("\n");
    }

    public abstract List<Message> process(MessageProcessHelper helper);
}
