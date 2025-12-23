package es.upm.etsisi.poo.exceptions.product;

public class PersonalitationUnformattedException extends RuntimeException {
    public PersonalitationUnformattedException() {
        super("Error, las personalizaciones deben tener el formato --p<Texto>");
    }
}
