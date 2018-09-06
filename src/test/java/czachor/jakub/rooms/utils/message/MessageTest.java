package czachor.jakub.rooms.utils.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void isCommandFalseTest() {
        Message message = new Message();
        message.setLine("notacommand");
        assertFalse(message.isCommand());
    }

    @Test
    public void isCommandTrueTest() {
        Message message = new Message();
        message.setLine("/command");
        assertTrue(message.isCommand());
    }

    @Test
    public void jsonTest(){
        Message message = new Message("author", "key", MessageType.COMMAND);
        message.setLine("text");
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writeValueAsString(message);
            assertTrue(jsonInString.contains("\"from\""));
            assertTrue(jsonInString.contains("\"line\""));
            assertTrue(jsonInString.contains("\"roomKey\""));
            assertTrue(jsonInString.contains("\"type\""));
        } catch (JsonProcessingException e) {
            fail();
        }
    }
}