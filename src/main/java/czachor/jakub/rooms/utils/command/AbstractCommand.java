package czachor.jakub.rooms.utils.command;

import czachor.jakub.rooms.config.ActiveUsers;
import czachor.jakub.rooms.dao.RoomDao;
import czachor.jakub.rooms.dao.SignatureDao;
import czachor.jakub.rooms.dao.StatisticsDao;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;

import java.util.List;

public abstract class AbstractCommand  extends CommandFieldsLoader{
    private String firstParam;
    private String secondParam;
    private String lastParam;
    protected RoomDao roomDao;
    protected SignatureDao signatureDao;
    protected StatisticsDao statisticsDao;
    protected UserDao userDao;
    protected ActiveUsers activeUsers;

    protected String firstParam(){
        if(this.firstParam == null){
            this.firstParam = detailsLoader.details().pickFirst();
        }
        return this.firstParam;
    }

    protected String secondParam(){
        if(this.secondParam == null){
            this.secondParam = detailsLoader.details().pickSecond();
        }
        return this.secondParam;
    }

    protected String lastParam(){
        if(this.lastParam == null){
            this.lastParam = detailsLoader.details().pickLast();
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
