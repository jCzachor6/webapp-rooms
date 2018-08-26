package czachor.jakub.rooms.exceptions;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class SignatureAlreadyExistsExceptionTest {
    @Test
    public void constructorTest(){
        String message = "test message";
        SignatureAlreadyExistsException signatureAlreadyExistsException = new SignatureAlreadyExistsException(message);
        assertEquals(message, signatureAlreadyExistsException.getMessage());
    }

    @Test
    public void instanceOfRuntimeException() {
        assertThat(new SignatureAlreadyExistsException(), instanceOf(RuntimeException.class));
    }
}