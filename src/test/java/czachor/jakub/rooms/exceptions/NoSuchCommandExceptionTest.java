package czachor.jakub.rooms.exceptions;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class NoSuchCommandExceptionTest {
    @Test
    public void constructorTest(){
        String message = "test message";
        NoSuchCommandException noSuchCommandException = new NoSuchCommandException(message);
        assertEquals(message, noSuchCommandException.getMessage());
    }

    @Test
    public void instanceOfRuntimeException() {
        assertThat(new NoSuchCommandException(), instanceOf(RuntimeException.class));
    }

}