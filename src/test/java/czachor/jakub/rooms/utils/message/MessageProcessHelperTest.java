package czachor.jakub.rooms.utils.message;

import czachor.jakub.rooms.utils.WebsocketUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageProcessHelperTest {

    @Test
    public void defaultDestinationTest() {
        MessageProcessHelper helper = new MessageProcessHelper(new WebsocketUser(), "key", "id");
        assertEquals(Destination.Target.ROOM, helper.getDestination().getTarget());
        assertEquals("key", helper.getDestination().getTargetName());
    }

    @Test
    public void sendBackToUserTest() {
        MessageProcessHelper helper = new MessageProcessHelper(new WebsocketUser(), "key", "id");
        helper.sendBackToUser();
        assertEquals(Destination.Target.USER, helper.getDestination().getTarget());
        assertEquals("id", helper.getDestination().getTargetName());
    }
}