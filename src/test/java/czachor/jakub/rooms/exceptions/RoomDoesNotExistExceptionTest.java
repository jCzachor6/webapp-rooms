package czachor.jakub.rooms.exceptions;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class RoomDoesNotExistExceptionTest {
    @Test
    public void constructorTest(){
        String message = "test message";
        RoomDoesNotExistException roomDoesNotExistException = new RoomDoesNotExistException(message);
        assertEquals(message, roomDoesNotExistException.getMessage());
    }

    @Test
    public void instanceOfRuntimeException() {
        assertThat(new RoomDoesNotExistException(), instanceOf(RuntimeException.class));
    }
}