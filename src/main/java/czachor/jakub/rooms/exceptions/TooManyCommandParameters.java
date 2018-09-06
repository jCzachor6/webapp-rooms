package czachor.jakub.rooms.exceptions;

public class TooManyCommandParameters extends RuntimeException {
    public TooManyCommandParameters() {
        super();
    }

    public TooManyCommandParameters(String message) {
        super(message);
    }
}
