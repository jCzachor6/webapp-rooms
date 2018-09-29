package czachor.jakub.rooms.utils.command;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandDetailsLoaderTest {

    @Test
    public void loadCommandTest() {
        CommandDetailsLoader loader = new CommandDetailsLoader("/command");
        assertEquals("command", loader.getCommand());
    }

    @Test
    public void loadCommandWithSpaceTest() {
        CommandDetailsLoader loader = new CommandDetailsLoader("/command ");
        assertEquals("command", loader.getCommand());
    }

    @Test
    public void loadCommandWithParamsTest() {
        CommandDetailsLoader loader = new CommandDetailsLoader("/command param1 param2");
        assertEquals("command", loader.getCommand());
    }

    @Test
    public void loadRestOfLineIfNoParamsTest() {
        CommandDetailsLoader loader = new CommandDetailsLoader("/command");
        assertEquals("", loader.getRestOfLine());
    }

    @Test
    public void loadRestOfLineTest() {
        CommandDetailsLoader loader = new CommandDetailsLoader("/command param1 param2");
        assertEquals("param1 param2", loader.getRestOfLine());
    }

    @Test
    public void pickFirstParamTest() {
        CommandDetailsLoader loader = new CommandDetailsLoader("/command first");
        String firstParam = loader.with(1).pickFirst();
        assertEquals("first", firstParam);

        CommandDetailsLoader loader2 = new CommandDetailsLoader("/command first param with spaces");
        String firstParam2 = loader2.with(1).pickFirst();
        assertEquals("first param with spaces", firstParam2);

        CommandDetailsLoader loader3 = new CommandDetailsLoader("/command first second third");
        String firstParam3 = loader3.with(2).pickFirst();
        assertEquals("first", firstParam3);

        CommandDetailsLoader loader4 = new CommandDetailsLoader("/command first second third");
        String firstParam4 = loader4.with(15).pickFirst();
        assertEquals("first", firstParam4);
    }

    @Test
    public void pickSecondParamTest() {
        CommandDetailsLoader loader = new CommandDetailsLoader("/command first");
        String second = loader.with(2).pickSecond();
        assertEquals("", second);

        CommandDetailsLoader loader2 = new CommandDetailsLoader("/command first second third");
        String second2 = loader2.with(2).pickSecond();
        assertEquals("second third", second2);

        CommandDetailsLoader loader3 = new CommandDetailsLoader("/command first second third");
        String second3 = loader3.with(3).pickSecond();
        assertEquals("second", second3);

        CommandDetailsLoader loader4 = new CommandDetailsLoader("/command first second third");
        String second4 = loader4.with(15).pickSecond();
        assertEquals("second", second4);
    }

    @Test
    public void pickLastParamTest(){
        CommandDetailsLoader loader = new CommandDetailsLoader("/command first");
        String last = loader.with(0).pickLast();
        assertEquals("", last);

        CommandDetailsLoader loader1 = new CommandDetailsLoader("/command first");
        String last1 = loader1.with(1).pickLast();
        assertEquals("first", last1);

        CommandDetailsLoader loader2 = new CommandDetailsLoader("/command first param");
        String last2 = loader2.with(1).pickLast();
        assertEquals("first param", last2);

        CommandDetailsLoader loader3 = new CommandDetailsLoader("/command first second");
        String last3 = loader3.with(2).pickLast();
        assertEquals("second", last3);
    }
}