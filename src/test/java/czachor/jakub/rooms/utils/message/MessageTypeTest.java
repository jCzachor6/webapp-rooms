package czachor.jakub.rooms.utils.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MessageTypeTest {
    @Test
    public void jsonTest() {
        MessageType type = MessageType.JOIN;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writeValueAsString(type);
            assertTrue(jsonInString.contains("color"));
        } catch (JsonProcessingException e) {
            fail();
        }
    }
}