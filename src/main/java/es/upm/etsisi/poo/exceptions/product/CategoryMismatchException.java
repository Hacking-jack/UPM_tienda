package es.upm.etsisi.poo.exceptions.product;

public class CategoryMismatchException extends RuntimeException {
    public CategoryMismatchException() {
        super("Categoría no válida");
    }
}
