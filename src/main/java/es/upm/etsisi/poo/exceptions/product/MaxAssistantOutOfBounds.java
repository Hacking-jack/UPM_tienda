package es.upm.etsisi.poo.exceptions.product;

public class MaxAssistantOutOfBounds extends IllegalArgumentException {
    public MaxAssistantOutOfBounds() {
        super("Las reuniones y las comidas pueden tener como máximo 100 asistentes");
    }
}
