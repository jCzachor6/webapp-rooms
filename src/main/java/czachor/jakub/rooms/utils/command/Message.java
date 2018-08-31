package czachor.jakub.rooms.utils.command;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
public class Message {
    private String from;
    private String line;

    public Message(String line, String from) {
        this.from = from;
        this.line = line;
    }

    public boolean isCommand(){
        if(!StringUtils.isEmpty(line)){
            return line.charAt(0) == '/';
        }
        return false;
    }
}
