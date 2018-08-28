package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.exceptions.TooManyCommandParameters;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.Message;
import czachor.jakub.rooms.utils.command.StringCommandResolver;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class TipCommandTest {
    @Test
    public void throwOnExceedingMaxParametersTest(){
        try {
            Command command = new StringCommandResolver().resolve(new Message("/tip \"ff\"", "author"));
            fail();
        }catch (TooManyCommandParameters e){
            assertEquals("This command requires maximum 0 parameters", e.getMessage());
        }
    }

    @Test
    public void verifyInstanceOf(){
        Command command = new StringCommandResolver().resolve(new Message("/tip", "author"));
        assertThat(command, instanceOf(TipCommand.class));
    }
}