package czachor.jakub.rooms.exceptions;

public class UsernameNotAvailableException extends RuntimeException {
    public UsernameNotAvailableException() {
        super();
    }

    public UsernameNotAvailableException(String message) {
        super(message);
    }
}
