package es.upm.etsisi.poo.exceptions.general;

public class UnknownCommandException extends RuntimeException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
