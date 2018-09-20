package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class RollCommandTest {
    @Test
    public void extendsCommandTest() {
        assertThat(new RollCommand(Collections.emptyList()), instanceOf(Command.class));
    }

    @Test
    public void processTest() {
        MessageProcessHelper helper = mock(MessageProcessHelper.class);
        RollCommand rollCommand = new RollCommand(Collections.emptyList());
        Message result = rollCommand.process(helper);
        int max = rollCommand.getMax();
        int rolled = rollCommand.getRolled();
        assertEquals(100, max);
        assertTrue(rolled<=100);
        assertTrue(rolled>=0);
        assertEquals("Rolled " + rolled + " out of " + max, result.getLine());
        assertEquals(Consts.BOT_NAME, result.getFrom());
    }
}