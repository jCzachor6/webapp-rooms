package czachor.jakub.rooms.utils.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    private String from;
    private String line;
    private MessageType type;
    @JsonIgnore
    private Destination destination;
}
