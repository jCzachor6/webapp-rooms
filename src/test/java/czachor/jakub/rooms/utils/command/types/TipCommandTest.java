package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class TipCommandTest {

    @Test
    public void extendsCommandTest() {
        assertThat(new TipCommand(Collections.emptyList()), instanceOf(Command.class));
    }

    @Test
    public void processTest() {
        MessageProcessHelper helper = mock(MessageProcessHelper.class);
        TipCommand command = new TipCommand(Collections.emptyList());
        Message message = command.process(helper);
        assertEquals(Consts.WORKING_COMMANDS.size(), StringUtils.countMatches(message.getLine(), "\n"));
        assertEquals(Consts.BOT_NAME, message.getFrom());
        verify(helper, times(1)).sendBackToUser();
    }
}