package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.message.Message;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class EchoCommandTest {

    @Test
    public void extendsCommandTest() {
        assertThat(new EchoCommand("", Collections.singletonList("param")), instanceOf(Command.class));
    }

    @Test
    public void processTest() {
        EchoCommand echoCommand = new EchoCommand("author", Collections.singletonList("text"));
        Message result = echoCommand.process();
        assertEquals("echo: text", result.getLine());
        assertEquals("author", result.getFrom());
    }
}