package es.upm.etsisi.poo.exceptions.human;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
