package czachor.jakub.rooms.exceptions;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class TooManyCommandParametersTest {
    @Test
    public void constructorTest() {
        String message = "test message";
        TooManyCommandParameters tooManyCommandParameters = new TooManyCommandParameters(message);
        assertEquals(message, tooManyCommandParameters.getMessage());
    }

    @Test
    public void instanceOfRuntimeException() {
        assertThat(new TooManyCommandParameters(), instanceOf(RuntimeException.class));
    }
}