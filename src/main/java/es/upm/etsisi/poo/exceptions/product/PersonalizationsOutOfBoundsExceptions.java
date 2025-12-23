package es.upm.etsisi.poo.exceptions.product;

public class PersonalizationsOutOfBoundsExceptions extends RuntimeException {
    public PersonalizationsOutOfBoundsExceptions() {
        super("No se ha podido añadir las personalizaciones, se ha alcanzado el máximo");
    }
}
