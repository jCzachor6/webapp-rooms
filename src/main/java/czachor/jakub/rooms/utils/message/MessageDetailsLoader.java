package czachor.jakub.rooms.utils.message;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MessageDetailsLoader {
    public List<String> retrieveDetails(Message message) {
        String[] details = message.getLine().split("\"");
        List<String> detailsNoWhitespaces = new ArrayList<>();
        for (int i = 1 /*ignore first*/; i<details.length; i++) {
            boolean shouldAdd = !StringUtils.isWhitespace(details[i]);
            if (shouldAdd) {
                detailsNoWhitespaces.add(details[i]);
            }
        }
        return detailsNoWhitespaces;
    }

    public int getIndexOfFirstSpaceOrEnd(Message message){
        String s = message.getLine();
        if(s.contains(" ")){
            return s.indexOf(" ");
        }else{
            return s.length();
        }
    }
}
