package es.upm.etsisi.poo.exceptions.product;

public class NotCustomizableProductException extends RuntimeException {
    public NotCustomizableProductException() {
        super("No se puede personalizar un objeto no personalizable");
    }
}
