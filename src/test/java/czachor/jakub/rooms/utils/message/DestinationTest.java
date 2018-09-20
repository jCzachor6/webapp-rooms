package czachor.jakub.rooms.utils.message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DestinationTest {
    @Test
    public void constructorTest() {
        Destination destination = new Destination(Destination.Target.ROOM, "key");
        assertEquals(Destination.Target.ROOM, destination.getTarget());
        assertEquals("key", destination.getTargetName());
    }
}