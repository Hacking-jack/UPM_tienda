package es.upm.etsisi.poo.exceptions.product;

public class DuplicateProductIdException extends RuntimeException {
    public DuplicateProductIdException() {
        super("Error. Ya existe un producto con ese id");
    }
}
