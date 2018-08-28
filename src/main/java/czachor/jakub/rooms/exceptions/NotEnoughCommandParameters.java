package czachor.jakub.rooms.exceptions;

public class NotEnoughCommandParameters extends RuntimeException {
    public NotEnoughCommandParameters() {
        super();
    }

    public NotEnoughCommandParameters(String message) {
        super(message);
    }
}
