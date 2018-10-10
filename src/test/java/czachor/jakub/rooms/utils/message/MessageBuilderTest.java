package czachor.jakub.rooms.utils.message;

import org.junit.Test;

import static org.junit.Assert.*;

public class MessageBuilderTest {

    @Test
    public void copyOfTest() {
        Message message = new Message();
        Destination destination = new Destination(Destination.Target.USER, "user");
        message.setDestination(destination);
        message.setType(MessageType.COMMAND);
        message.setLine("line");
        message.setFrom("from");

        Message copy = new MessageBuilder().copyOf(message).build();
        assertEquals(message, copy);
    }
}