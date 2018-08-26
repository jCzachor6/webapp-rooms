package czachor.jakub.rooms.exceptions;

public class RoomDoesNotExistException extends RuntimeException {
    public RoomDoesNotExistException() {
        super();
    }

    public RoomDoesNotExistException(String message) {
        super(message);
    }
}
