package es.upm.etsisi.poo.exceptions.product;

public class IdUnformattedException extends RuntimeException {
    public IdUnformattedException() {
        super("El id del producto debe ser un número");
    }
}
