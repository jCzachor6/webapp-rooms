package czachor.jakub.rooms.utils;

import czachor.jakub.rooms.exceptions.NoSuchCommandException;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.command.Message;
import czachor.jakub.rooms.utils.command.StringCommandResolver;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTest {
    @Test
    public void resolveThrowExceptionTest() {
        try {
            Message message = new Message("/rerere \"text1\" \"text2\" \"text3\"", "author");
            new StringCommandResolver().resolve(message);
            fail();
        }catch (NoSuchCommandException e){
            assertEquals("Command \'rerere\' doesn't exist", e.getMessage());
        }
    }
}