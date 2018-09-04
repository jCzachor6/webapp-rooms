package czachor.jakub.rooms.utils.message;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MessageTest {

    @Test
    public void isCommandFalseTest() {
        Message message = new Message("notacommand", "author");
        assertFalse(message.isCommand());
    }

    @Test
    public void isTrueFalseTest() {
        Message message = new Message("/command", "author");
        assertTrue(message.isCommand());
    }
}