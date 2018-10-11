package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.message.Destination;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;
import czachor.jakub.rooms.utils.message.MessageType;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;


public class EchoCommandTest {
    @Mock
    private MessageProcessHelper helper;

    private EchoCommand command;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        when(helper.getSessionId()).thenReturn("id");
        command = new EchoCommand();
    }

    @Test
    public void processWithOneParam() throws IllegalAccessException {
        FieldUtils.writeField(command, "firstParam", "param", true);

        List<Message> messages = command.process(helper);
        assertEquals(1, messages.size());
        Message result = messages.get(0);
        assertEquals(Consts.BOT_NAME, result.getFrom());
        assertEquals("param", result.getLine());
        assertEquals(Destination.Target.USER, result.getDestination().getTarget());
        assertEquals("id", result.getDestination().getTargetName());
        assertEquals(MessageType.COMMAND, result.getType());
    }

    @Test
    public void processWithNoParams() throws IllegalAccessException {
        FieldUtils.writeField(command, "firstParam", "", true);

        List<Message> messages = command.process(helper);
        assertEquals(1, messages.size());
        Message result = messages.get(0);
        assertEquals(Consts.BOT_NAME, result.getFrom());
        assertEquals("ECHO", result.getLine());
        assertEquals(Destination.Target.USER, result.getDestination().getTarget());
        assertEquals("id", result.getDestination().getTargetName());
        assertEquals(MessageType.COMMAND, result.getType());
    }
}