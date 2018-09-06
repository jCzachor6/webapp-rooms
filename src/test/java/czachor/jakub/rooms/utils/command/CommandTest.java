package czachor.jakub.rooms.utils.command;

import czachor.jakub.rooms.exceptions.NotEnoughCommandParameters;
import czachor.jakub.rooms.exceptions.TooManyCommandParameters;
import czachor.jakub.rooms.utils.message.Message;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CommandTest {

    class A extends Command {
        public A(List<String> details) {
            super(details);
            setType(CommandType.ECHO);
        }

        @Override
        public Message process(String from, String roomkey) {
            return null;
        }
    }

    @Test(expected = NotEnoughCommandParameters.class)
    public void throwOnNotEnoughParametersTest() {
        A command = new A(Collections.emptyList());
    }

    @Test(expected = TooManyCommandParameters.class)
    public void throwOnTooManyParameters() {
        A command = new A(Arrays.asList("param1", "param2", "param3"));
    }

    @Test
    public void buildIndexedLineTest() {
        StringBuilder builder = new StringBuilder();
        A a = new A(Collections.singletonList("param"));
        a.buildIndexedLine(builder, 0, "content");
        assertEquals("1. content\n", builder.toString());
    }
}