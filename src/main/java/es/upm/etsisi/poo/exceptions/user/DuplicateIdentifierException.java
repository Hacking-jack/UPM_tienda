package es.upm.etsisi.poo.exceptions.user;

public class DuplicateIdentifierException extends RuntimeException {
    public DuplicateIdentifierException(String id) {
        super("Error, ya existe un usuario con el id: " + id);
    }
}
