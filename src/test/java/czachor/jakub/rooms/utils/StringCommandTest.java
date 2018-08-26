package czachor.jakub.rooms.utils;

import czachor.jakub.rooms.exceptions.NoSuchCommandException;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.command.StringCommand;
import czachor.jakub.rooms.utils.command.StringCommandResolver;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCommandTest {

    @Test
    public void resolveTest() {
        StringCommand stringCommand = new StringCommand("/tip \"text1\" \"text2\" \"text3\"");
        Command command = new StringCommandResolver().resolve(stringCommand);
        assertEquals(3, command.getDetails().size());
        assertEquals(CommandType.TIP, command.getType());
        assertEquals("text1", command.getDetails().get(0));
        assertEquals("text2", command.getDetails().get(1));
        assertEquals("text3", command.getDetails().get(2));
    }

    @Test
    public void resolveThrowExceptionTest() {
        try {
            StringCommand stringCommand = new StringCommand("/rerere \"text1\" \"text2\" \"text3\"");
            new StringCommandResolver().resolve(stringCommand);
            fail();
        }catch (NoSuchCommandException e){
            assertEquals("Command \'rerere\' doesn't exist", e.getMessage());
        }
    }

}