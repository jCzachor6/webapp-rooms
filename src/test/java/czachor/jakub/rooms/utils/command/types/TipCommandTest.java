package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.message.Message;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TipCommandTest {

    @Test
    public void extendsCommandTest() {
        assertThat(new TipCommand(Collections.emptyList()), instanceOf(Command.class));
    }

    @Test
    public void processTest() {
        TipCommand command = new TipCommand(Collections.emptyList());
        Message message = command.process("author", "key");
        assertEquals(Consts.WORKING_COMMANDS.size(), StringUtils.countMatches(message.getLine(), "\n"));
        assertEquals(Consts.BOT_NAME, message.getFrom());
    }
}