package czachor.jakub.rooms.exceptions;

public class SignatureAlreadyExistsException extends RuntimeException {
    public SignatureAlreadyExistsException() {
        super();
    }

    public SignatureAlreadyExistsException(String message) {
        super(message);
    }
}
