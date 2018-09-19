package czachor.jakub.rooms.utils.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MessageType {
    NORMAL("#becde8"),
    PRIVATE("#afafaf"),
    JOIN("#dbfff7"),
    LEAVE("#ffdedb"),
    COMMAND("#fcdbff"),
    SPECIAL("#fcffd6");
    @JsonProperty
    private String color;

    MessageType(String color) {
        this.color = color;
    }
}
