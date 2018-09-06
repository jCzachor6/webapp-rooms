package czachor.jakub.rooms.exceptions;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class NotEnoughCommandParametersTest {
    @Test
    public void constructorTest() {
        String message = "test message";
        NotEnoughCommandParameters notEnoughCommandParameters = new NotEnoughCommandParameters(message);
        assertEquals(message, notEnoughCommandParameters.getMessage());
    }

    @Test
    public void instanceOfRuntimeException() {
        assertThat(new NotEnoughCommandParameters(), instanceOf(RuntimeException.class));
    }
}