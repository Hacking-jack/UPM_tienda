package es.upm.etsisi.poo.exceptions.product;

public class FoodMeetingCategoryException extends RuntimeException {
    public FoodMeetingCategoryException() {
        super("No se puede aplicar una categoría a comidas o reuniones");
    }
}
