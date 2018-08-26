package czachor.jakub.rooms.exceptions;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class UserDoesNotExistExceptionTest {
    @Test
    public void constructorTest(){
        String message = "test message";
        UserDoesNotExistException userDoesNotExistException = new UserDoesNotExistException(message);
        assertEquals(message, userDoesNotExistException.getMessage());
    }

    @Test
    public void instanceOfRuntimeException() {
        assertThat(new UserDoesNotExistException(), instanceOf(RuntimeException.class));
    }
}