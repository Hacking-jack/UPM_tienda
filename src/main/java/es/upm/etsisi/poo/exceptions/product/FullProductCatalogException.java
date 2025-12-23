package es.upm.etsisi.poo.exceptions.product;

public class FullProductCatalogException extends RuntimeException {
    public FullProductCatalogException() {
        super("Error, catálogo de productos lleno");
    }
}
