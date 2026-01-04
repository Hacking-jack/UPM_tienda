package es.upm.etsisi.poo.exceptions.product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String id) {
        super("Error, no se encontró el producto con id " + id);
    }
}
