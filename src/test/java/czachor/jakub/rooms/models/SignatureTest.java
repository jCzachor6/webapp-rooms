package czachor.jakub.rooms.models;

import static org.junit.Assert.*;

public class SignatureTest {
    public static Signature getSampleInstance(){
        Signature signature = new Signature();
        signature.setContent("content");
        return signature;
    }
}