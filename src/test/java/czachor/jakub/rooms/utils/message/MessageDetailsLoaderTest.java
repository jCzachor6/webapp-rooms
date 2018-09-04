package czachor.jakub.rooms.utils.message;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MessageDetailsLoaderTest {

    @Test
    public void retrieveDetailsTest() {
        String line = "/command \"det1\" \"det2\" \"det3\"";
        Message message = new Message(line, "author");
        List<String> details = new MessageDetailsLoader().retrieveDetails(message);
        assertEquals(3, details.size());
        assertEquals("det1", details.get(0));
        assertEquals("det2", details.get(1));
        assertEquals("det3", details.get(2));
    }

    @Test
    public void retrieveDetailsEmptyTest() {
        String line = "/command";
        Message message = new Message(line, "author");
        List<String> details = new MessageDetailsLoader().retrieveDetails(message);
        assertTrue(details.isEmpty());
    }

    @Test
    public void retrieveDetailsBackslashTest() {
        String line = "/";
        Message message = new Message(line, "author");
        List<String> details = new MessageDetailsLoader().retrieveDetails(message);
        assertTrue(details.isEmpty());
    }

    @Test
    public void retrieveDetailsOnlyQuotesTest() {
        String line = "/\"    \"   \" \"\"  ";
        Message message = new Message(line, "author");
        List<String> details = new MessageDetailsLoader().retrieveDetails(message);
        assertTrue(details.isEmpty());
    }

    @Test
    public void getIndexOfFirstSpaceTest() {
        String line = "/command \"det1\" \"det2\" \"det3\"";
        Message message = new Message(line, "author");
        int index = new MessageDetailsLoader().getIndexOfFirstSpaceOrEnd(message);
        assertEquals(8, index);
    }

    @Test
    public void getIndexOfEndTest() {
        String line = "/command";
        Message message = new Message(line, "author");
        int index = new MessageDetailsLoader().getIndexOfFirstSpaceOrEnd(message);
        assertEquals(8, index);
    }
}