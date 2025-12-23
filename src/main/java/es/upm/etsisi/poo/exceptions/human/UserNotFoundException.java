package es.upm.etsisi.poo.exceptions.human;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("No se encontró al usuario con id: " + id);
    }
}
