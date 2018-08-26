package czachor.jakub.rooms.exceptions;

public class NoSuchCommandException extends RuntimeException {
    public NoSuchCommandException() {
        super();
    }

    public NoSuchCommandException(String message) {
        super(message);
    }
}
