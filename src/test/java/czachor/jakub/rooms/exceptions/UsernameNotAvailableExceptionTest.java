package czachor.jakub.rooms.exceptions;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UsernameNotAvailableExceptionTest {
    @Test
    public void constructorTest() {
        String message = "test message";
        UsernameNotAvailableException exception = new UsernameNotAvailableException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void instanceOfRuntimeException() {
        assertThat(new UsernameNotAvailableException(), instanceOf(RuntimeException.class));
    }
}